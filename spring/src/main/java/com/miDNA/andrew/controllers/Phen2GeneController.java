package com.miDNA.andrew.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.miDNA.andrew.dto.HmName2Id;
import com.miDNA.andrew.dto.HpoDto;
import com.miDNA.andrew.dto.PhenGeneDto;
import com.miDNA.andrew.dto.Phene2Gene;
import com.miDNA.andrew.entities.HpoTerm;
import com.miDNA.andrew.entities.PhenGene;
import com.miDNA.andrew.services.HpoTermService;
import com.miDNA.andrew.services.PhenGeneService;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.*;
import javax.transaction.Transactional;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
@CrossOrigin
public class Phen2GeneController {

    @Autowired
    private PhenGeneService phenGeneService;

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

    // Need to convert an ArrayList<HpoTerms> its corresponding ArrayList<HpoIDs> for this method to work
    @GetMapping(value = "/phen2gene")
    public ArrayList<PhenGene> getPhen2GeneData(String hpos) throws IOException{
        ArrayList<String> hpoIDs = new ArrayList<>(Arrays.asList(hpos.split(",")));
        System.out.println("Processing "+hpoIDs.size()+" Hpo's");
        // Initalize an array of phen genes by passing in a list of Hpo ter
        ArrayList<PhenGene> allGenes = new ArrayList<>();
        for(String hpo: hpoIDs) {
            ArrayList<PhenGene> phenGenes = getGeneData(hpo);
            // looping through all phengenes for one HPO then adding them to the main list of phengenes
            for(PhenGene pheneGene : phenGenes)
            {
                allGenes.add(pheneGene);
            }
            System.out.println("Finished Processing HPO : "+hpo);
        }

        return allGenes;
    }

    @Transactional
    public ArrayList<PhenGene> getGeneData(String hpo) throws IOException {

        // Initalize an array of phen genes by passing in a list of Hpo terms
        ArrayList<PhenGene> phenGenes = phenGeneService.getAllPhenGenesByHpoID(hpo);
        // Return from database here if already stored in database where key = ArrayList<HpoTerm>
        if(!phenGenes.isEmpty()) {
            System.out.println("Returning Phen2Gene genes from Database for HPO: "+hpo);
            return phenGenes;
        }
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");

        String requestParams = "";
        String hpoID=hpo;
        hpoID=hpoID.replaceAll(":","%3A");
        requestParams+=hpoID;
        // System.out.println("https://phen2gene.wglab.org/api?HPO_list="+requestParams);
        Request request = new Request.Builder()
                .url("https://phen2gene.wglab.org/api?HPO_list="+requestParams)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        String json = response.body().string();
        Gson gson = new Gson(); // Or use new GsonBuilder().create();
        json = json.replaceAll("Gene ID","geneID").replaceAll("Gene","gene").replaceAll("Rank","rank").replaceAll("Score","score").replaceAll("Status","status");
        // System.out.println(json);
        PhenGeneDto phenGeneData = gson.fromJson(json, PhenGeneDto.class);
        // System.out.println(phenGeneData);

        for(Phene2Gene phengene : phenGeneData.getResults())
        {
            //System.out.println("Before Mapping Object to entity:"+phengene);
            PhenGene phenGene = new PhenGene(phengene.getGene(), phengene.getRank(), phengene.getScore(), phengene.getStatus(), hpo);
            //System.out.println("After Mapping Object to entity:"+phenGene);
            phenGeneService.savePhenGene(phenGene);
            phenGenes.add(phenGene);
            // System.out.println(phenGene);
        }
        System.out.println("Returning Phen2Gene genes from API for HPO: "+hpo);

        return phenGenes;
    }


}


