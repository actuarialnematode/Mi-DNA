package com.miDNA.andrew.controllers;

import com.google.gson.Gson;
import com.miDNA.andrew.dto.HmName2Id;
import com.miDNA.andrew.dto.HpoDto;
import com.miDNA.andrew.entities.GenomeLocation;
import com.miDNA.andrew.entities.HpoTerm;
import com.miDNA.andrew.services.GenomeLocationService;
import com.miDNA.andrew.services.HpoTermService;
import com.mysql.cj.jdbc.Blob;
import okhttp3.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Iterator;


@RestController
@CrossOrigin
public class Doc2HPOController {

    @Autowired
    private HpoTermService hpoTermService;

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

    @GetMapping(value = "/doc2hpo")
    public ArrayList<HpoTerm> getDoc2HPOData(@RequestParam String text) throws IOException {

        // Use getBytes here because String limit on database
        // ArrayList<Type> typeName = typeService.getAllTypeByKey(key);
        ArrayList<HpoTerm> hpoTerms = hpoTermService.getAllHpoByText(text.getBytes("UTF-8"));
        // Checking if text is already in database; if so, no need to return from API
        if(!hpoTerms.isEmpty())
        {
            System.out.println("Returning HPO terms from Database.");
            return hpoTerms;
        }

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");

//      TODO: need to pass in String variable
        RequestBody body = RequestBody.create(mediaType, "{\"note\":\""+text+"\",\"allowPartial\":false,\"negex\":true}");
        Request request = new Request.Builder()
                .url("https://doc2hpo.wglab.org/parse/acdat")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        String json = response.body().string();
        Gson gson = new Gson(); // Or use new GsonBuilder().create();
        HpoDto hpoData = gson.fromJson(json, HpoDto.class);
        for(HmName2Id hpo : hpoData.getHmName2Id())
        {
            System.out.println("Before Mapping Object to entity:"+hpo);
            HpoTerm hpoTerm = new HpoTerm(hpo.getHpoId(),hpo.getHpoName(),text.getBytes("UTF-8"));
            System.out.println("After Mapping Object to entity:"+hpoTerm);
            hpoTermService.saveHpoTerm(hpoTerm);
            hpoTerms.add(hpoTerm);
            System.out.println(hpoTerm);
        }
        System.out.println("Returning HPO terms from API.");
        return hpoTerms;
    }


}
