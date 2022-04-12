package com.miDNA.andrew.controllers;


import com.miDNA.andrew.entities.GenomeLocation;
import com.miDNA.andrew.services.GenomeLocationService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.cert.CertificateException;
import java.util.*;

@RestController
@CrossOrigin
public class VarfishController {

    @Autowired
    private GenomeLocationService genomeLocationService;

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

    /**
     * Will retrieve all genome location data for a particular case
     * @param caseNumber
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/varfish/case/{caseNumber}")
    public ArrayList<GenomeLocation> getDataByCaseNumber(@PathVariable String caseNumber) throws IOException {
        return genomeLocationService.getAllLocationsByCase(caseNumber);
    }

    /**
     * Will retrive a list of all loaded case.
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/varfish/case/all")
    public ArrayList<String> getVarfishAllCases()throws IOException {
        ArrayList<GenomeLocation>genomeLocations = genomeLocationService.getAllLocations();
        HashSet<String>uniqueCaseNumber= new HashSet<>();
        genomeLocations.stream().forEach(genomeLoc->{
            uniqueCaseNumber.add(genomeLoc.getCaseNumber());
        });
        return new ArrayList<>(uniqueCaseNumber);
    }

    @GetMapping(value = "/varfish")
    public String getVarfishData() throws IOException {
        //ArrayList<String>urls = loadVarfishData();
        List<String> urls = Arrays.asList("/variants/a1ab6df9-5c9e-483b-bba1-afab33c53215/case/load-filter-results/d2e63483-db9c-43c1-9f4c-8fa46ed22fd0/?csrfmiddlewaretoken=o8zXJkip8UfEDXBaYzd4EUXN2bA8rbTBs6J69AZBCv1t8XjzmSgYxJxNbndK1ypn&filter_job_uuid=b19be57d-4e20-408e-abf1-e9683214821a" ,
                "/variants/a1ab6df9-5c9e-483b-bba1-afab33c53215/case/load-filter-results/7e822ee7-e241-469e-8bb8-4a0278c3cdb9/?csrfmiddlewaretoken=udYNpf8HymEYAtV5ksLRymz7HhPhPqGx0J1dCDZ2x9HX4eDzfpzCaYlBSU5DHyez&filter_job_uuid=2852fc65-e7b4-4eb2-bb30-4c8bf96c3d87");
        for (String urlBase : urls)
        {
            importCase(urlBase);
            System.out.println("Finished importing "+ urlBase);
        }
        return "Complete";

    }

    public void importCase(String urlBase) throws IOException {
        OkHttpClient client = getUnsafeOkHttpClient();
        //8af98ed5e181ae2eeac769f5c3558a7249fd851453c9129bc784def44a6e0c12
        //https://localhost:8080/variants/a1ab6df9-5c9e-483b-bba1-afab33c53215/case/load-filter-results/d2e63483-db9c-43c1-9f4c-8fa46ed22fd0/?csrfmiddlewaretoken=o8zXJkip8UfEDXBaYzd4EUXN2bA8rbTBs6J69AZBCv1t8XjzmSgYxJxNbndK1ypn&filter_job_uuid=b19be57d-4e20-408e-abf1-e9683214821a
        //https://localhost:8080/variants/a1ab6df9-5c9e-483b-bba1-afab33c53215/case/load-filter-results/7e822ee7-e241-469e-8bb8-4a0278c3cdb9/?csrfmiddlewaretoken=udYNpf8HymEYAtV5ksLRymz7HhPhPqGx0J1dCDZ2x9HX4eDzfpzCaYlBSU5DHyez&filter_job_uuid=2852fc65-e7b4-4eb2-bb30-4c8bf96c3d87
        //https://localhost:8080/variants/a1ab6df9-5c9e-483b-bba1-afab33c53215/case/load-filter-results/7e822ee7-e241-469e-8bb8-4a0278c3cdb9/?csrfmiddlewaretoken=udYNpf8HymEYAtV5ksLRymz7HhPhPqGx0J1dCDZ2x9HX4eDzfpzCaYlBSU5DHyez&filter_job_uuid=b19be57d-4e20-408e-abf1-e9683214821a
        System.out.println("https://localhost:8080"+urlBase);
        Request request = new Request.Builder()
                .url("https://localhost:8080"+urlBase)
                .method("GET", null)
                .addHeader("cookie", "csrftoken=o8zXJkip8UfEDXBaYzd4EUXN2bA8rbTBs6J69AZBCv1t8XjzmSgYxJxNbndK1ypn; sessionid=gt2t3wx7ms389k0ku3dnk54lw60jul94; csrftoken=w5Iiw875rzBW9SfTYyvxBuF1NGELWITNN4AqIPKOLOqACLolvmh0v3i1PIeYPaQ3")
                .build();
        Response response = client.newCall(request).execute();

        String html = response.body().string();

        Document doc = Jsoup.parse(html);
        ArrayList<String> varfishTableData = new ArrayList<>();

        Elements tables = doc.getElementsByTag("table");

        //System.out.println("Tables : "+tables.size());
        for (Element table : tables) {


            Elements tableRows = table.getElementsByTag("tr");
            //System.out.println("Table Rows : "+tableRows.size());

            Elements tableHeaders = table.getElementsByTag("th");
            //System.out.println("Table Headers : "+tableHeaders.size());

            String caseNumber="";
            for (Element tableHeader: tableHeaders)
            {
                Elements headerData = tableHeader.getElementsByClass("sodar-overflow-container sodar-overflow-hover");
                String [] dataFields = headerData.text().split(" ");
                for(String field: dataFields) {
                    if(field.length()>0) {
                        System.out.println("Case number: " + field);
                        caseNumber=field;
                        break;
                    }
                }
            }

            for (Element tableRow : tableRows) {
                int counter = 0;
                Elements tableData = tableRow.getElementsByTag("td");
                String [] dataFields = tableData.text().split(" ");
                ArrayList<String> geneLocData = new ArrayList();
                for (String field : dataFields)
                {

                    if(field.length()>0) {
                        counter++;
                        if(counter > 2 && counter < 38) {
                            //System.out.println(counter + " : " + field + "\n");
                            geneLocData.add(field);
                        }
                    }
                }
                System.out.println(geneLocData);
                if(geneLocData.size()>30) {
                    geneLocData.add(caseNumber);
                    GenomeLocation genomeLocation = new GenomeLocation(geneLocData);
                    genomeLocationService.saveGenomeLocation(genomeLocation);
                    System.out.println(genomeLocation);
                    System.out.println("\n\n\n");
                }
            }

        }
    }

    public ArrayList<String> loadVarfishData() throws IOException {

        OkHttpClient client = getUnsafeOkHttpClient();


        Request request = new Request.Builder()
                .url("https://localhost:8080/project/a1ab6df9-5c9e-483b-bba1-afab33c53215")
                .method("GET", null)
                .addHeader("cookie", "csrftoken=o8zXJkip8UfEDXBaYzd4EUXN2bA8rbTBs6J69AZBCv1t8XjzmSgYxJxNbndK1ypn; sessionid=gt2t3wx7ms389k0ku3dnk54lw60jul94; csrftoken=w5Iiw875rzBW9SfTYyvxBuF1NGELWITNN4AqIPKOLOqACLolvmh0v3i1PIeYPaQ3")
                .build();
        Response response = client.newCall(request).execute();

        String html = response.body().string();

        Document doc = Jsoup.parse(html);
        ArrayList<String> links = new ArrayList<>();

        Elements linksHtml = doc.select("a[href]");
        for(Element linkHtml: linksHtml){
            String href= linkHtml.attr("href");
            if(href.startsWith("/variants/") && href.contains("case/filter")) {
                links.add(href);
            }
        }
        return links;
    }

}