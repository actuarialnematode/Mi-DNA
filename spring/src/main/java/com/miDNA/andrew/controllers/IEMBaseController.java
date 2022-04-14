package com.miDNA.andrew.controllers;

import com.google.gson.Gson;
import com.jaunt.JNode;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;
import com.miDNA.andrew.dto.Iem2BaseGene;
import com.miDNA.andrew.dto.IemGeneCheckerDto;
import com.miDNA.andrew.dto.PhenGeneDto;
import com.miDNA.andrew.dto.Phene2Gene;
import com.miDNA.andrew.dto.iem.IemDto;
import com.miDNA.andrew.entities.*;
import com.miDNA.andrew.services.IEMGeneService;
import com.miDNA.andrew.services.IEMService;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.*;
import javax.transaction.Transactional;
import java.io.*;
import java.lang.reflect.Array;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

@RestController
@CrossOrigin
public class IEMBaseController {

    @Autowired
    private IEMService iemService;

    @Autowired
    private IEMGeneService iemGeneService;

    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/iembase")
    public ArrayList<IEM> getIEMBaseData(@RequestParam(name="genes") String genes) throws IOException {
        String [] genesArr=genes.replaceAll("\\\"","").replaceAll("(\\[|\\])","").split(",");
        ArrayList<String> potentialIEMgenes = new ArrayList<>(Arrays.asList(genesArr));
        // limiting to 1000 gene query to prevent API blacklist from IEMBase
        potentialIEMgenes=new ArrayList<>(potentialIEMgenes.subList(0,1000));
        ArrayList<IEM> iemArray = new ArrayList<>();
        System.out.println("Process Starting processing :"+potentialIEMgenes.size());
        int count=0;;
        for (String potentialIEM : potentialIEMgenes) {
            //System.out.println(potentialIEM);
            String iemID = readIEMJson(potentialIEM);
            //System.out.println(iemID);
            if (iemID != null) {

                iemArray.add(getIEM(iemID));
            }
            count++;
            if(count%1000==0){
                System.out.println("Finished "+count+"/"+potentialIEMgenes.size());
            }
        }
        System.out.println("Finished process");

        return iemArray;
    }

    @Transactional
    public IEM getIEM(String iemID) throws IOException {
        IEM iem=new IEM();
//        iem= iemService.getAllIemsByGeneID(iemID);
//        if(iem!=null)
//        {
//            // System.out.print(iem);
//            return iem;
//        }

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        //if iemId is in database return iem db
//      TODO: need to make a request for all phen_gene's and store the IEM data
//      TODO: need to pass in phen_gene and convert iem_id to the end of URL where "/frontend/100" is

        // String iemID = readIEMJson(geneID);

        //System.out.println("iemID is " + iemID);
        Request request = new Request.Builder()
                .url("http://www.iembase.com/api/disorder/frontend/"+iemID)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();

        String json = response.body().string();

        Gson gson = new Gson();
        IemDto iemDto = gson.fromJson(json,IemDto.class);
        iem.setIem_geneid(iemID);
        iem.setIem_omim(iemDto.getOmimNo());

        // creates the biochemical marker object
        try{
            UserAgent userAgent = new UserAgent();
            userAgent.openJSON(json);  //open JSON from a file

            JNode biochemical = userAgent.json.findFirst("biochemical").findEvery("symptom");
            JNode symptomNameJson = biochemical.findEvery("name");
            JNode symptomTestJson = biochemical.findEvery("biochemical_test");
            JNode symptomHmdbJson = biochemical.findEvery("hmdb");

//            System.out.println("node name: " + symptomNameJson.toString());
//            System.out.println("node name: " + symptomTestJson.toString());
//            System.out.println("node name: " + symptomHmdbJson.toString());

            //System.out.println("node type: " + symptomJson.getFirst("biochemical_test").toString());
            ArrayList<String> names= new ArrayList<>();
            ArrayList<String> tests = new ArrayList<>();
            ArrayList<String> hmdbs = new ArrayList<>();
            try {
                names = new ArrayList<>(Arrays.asList(symptomNameJson.toString().substring(2, symptomNameJson.toString().length() - 3).split("\\\",\\\"")));
                tests = new ArrayList<>(Arrays.asList(symptomTestJson.toString().substring(1, symptomTestJson.toString().length() - 2).replaceAll("\"", "").split(",")));
                hmdbs = new ArrayList<>(Arrays.asList(symptomHmdbJson.toString().substring(1, symptomHmdbJson.toString().length() - 2).replaceAll("\"", "").split(",")));
            } catch(Exception e)
            {
                // iem is returned if there are no biochemical markers
                return iem;
            }

            // Debug purposes
            //            System.out.println("node name: " + names.toString());
//            System.out.println("node name: " + tests.toString());
//            System.out.println("node name: " + hmdbs.toString());
            ////System.out.println(names.size());
//            for (String each_name : names) {
//                //System.out.println(each_name);
//            }

            // saving to our DB
            for (int i = 0; i < names.size(); i++) {
                IEMSymptom temp = new IEMSymptom();
                try {
                    temp.setIemSymptom_name(names.get(i));
                } catch(Exception E) {
                    temp.setIemSymptom_name("");
                }
                try {
                    temp.setIemSymptom_biochemicalTest(tests.get(i));
                } catch(Exception E) {
                    temp.setIemSymptom_name("");
                }
                try {
                    temp.setIemSymptom_hmdb(hmdbs.get(i));
                } catch(Exception E) {
                    temp.setIemSymptom_name("");
                }
                iemService.saveIEMSymptom(temp);
                // this final line will add the array of iem symptoms to each iem
                iem.getIemSymptoms().add(temp);
            }
            // iem is now saved into DB
            iem=iemService.saveIEM(iem);
        }
        // if there is any error while reading JSON, exception is caught
        catch(JauntException e){
            System.err.println(e);
        }

//        IEMSymptom iemSymptom = new IEMSymptom();
//        iemSymptom.setIemSymptom_name("TEST Symp");
//
//        IEMSymptom iemSymptom2 = new IEMSymptom();
//        iemSymptom2.setIemSymptom_name("TEST2 Symp");
//
//        iemService.saveIEMSymptom(iemSymptom2);
//        IEM iem = new IEM();
//        iem.setIem_geneid("1");
//        iem.getIemSymptoms().add(iemSymptom);
//        iem.getIemSymptoms().add(iemSymptom2);
//        iemService.saveIEMSymptom(iemSymptom);
//        IEM saved = iemService.saveIEM(iem);

//        Parameters:
//        ArrayList<String> iemData = new ArrayList();
//        Returns:
//        IEM iem = new IEM(iemData);
//
//        TODO: use IEMService to save to database
//        iemService.saveIEM(iem);
//        System.out.println(iem);
//        System.out.println("\n\n\n");

        return iem;
    }

    public String readIEMJson(String gene) {
        try{
            IEMGene iemGene = iemGeneService.getIEMGeneByID(gene);
            //System.out.println("IEMGende:"+iemGene);
            return iemGene.getIem_id();
        }catch (Exception e)
        {
            return null;
        }
    }

    @GetMapping(value="/iembase/load")
    public void loadIEMFileToDB() throws IOException {
        try {
            //Parsing the contents of the JSON file
            BufferedReader bf = new BufferedReader(new FileReader("/home/andrewxu/Documents/andrew/src/main/resources/static/IEMbase_disorders_URLs.json"));
            String json = "";
            String line = "";
            while((line = bf.readLine())!= null)
            {
                json+=line;
            }

            Gson gson = new Gson();
            Iem2BaseGene iemGeneCheckerData = gson.fromJson(json, Iem2BaseGene.class);
            for (IemGeneCheckerDto iemGene : iemGeneCheckerData.getIemGenes()){
                IEMGene iem = new IEMGene();
                iem.setIem_id(Integer.toString(iemGene.getId()));
                iem.setIem_geneName(iemGene.getGene());
                iemGeneService.saveIEMGene(iem);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
