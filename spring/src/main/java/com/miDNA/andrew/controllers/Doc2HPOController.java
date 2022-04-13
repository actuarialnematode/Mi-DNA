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
import okhttp3.RequestBody;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;


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

    @PostMapping(value = "/doc2hpo")
    public ArrayList<HpoTerm> getDoc2HPOData(@RequestParam(name="text") String text) throws IOException {

        // Use getBytes here because String limit on database
        // ArrayList<Type> typeName = typeService.getAllTypeByKey(key);
        // getBytes is to store into database since string is too large
        ArrayList<HpoTerm> hpoTerms = hpoTermService.getAllHpoByText(text.getBytes("UTF-8"));
        // Checking if text is already in database; if so, no need to return from API
        if(!hpoTerms.isEmpty())
        {
            System.out.println("Returning HPO terms from Database.");
            return hpoTerms;
        }

//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("application/json");
//        System.out.println(text);
////      TODO: need to pass in String variable
//        RequestBody body = RequestBody.create(mediaType, "{\"note\":\""+text+"\",\"allowPartial\":false,\"negex\":true}");
//        Request request = new Request.Builder()
//                .url("https://doc2hpo.wglab.org/parse/acdat")
//                .method("POST", body)
//                .addHeader("Content-Type", "application/json")
//                .build();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .callTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"note\":\""+encodeValue(text)+"\",\"allowPartial\":false,\"negex\":true}");
        Request request = new Request.Builder()
                .url("https://doc2hpo.wglab.org/parse/acdat")
                .method("POST", body)
                .addHeader("authority", "doc2hpo.wglab.org")
                .addHeader("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"98\", \"Google Chrome\";v=\"98\"")
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .addHeader("x-requested-with", "XMLHttpRequest")
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.102 Safari/537.36")
                .addHeader("sec-ch-ua-platform", "\"Linux\"")
                .addHeader("origin", "https://doc2hpo.wglab.org")
                .addHeader("sec-fetch-site", "same-origin")
                .addHeader("sec-fetch-mode", "cors")
                .addHeader("sec-fetch-dest", "empty")
                .addHeader("referer", "https://doc2hpo.wglab.org/")
                .addHeader("accept-language", "en-CA,en;q=0.9,fr-CA;q=0.8,fr;q=0.7,zh-CN;q=0.6,zh;q=0.5")
                .build();
        Response response = client.newCall(request).execute();
        String json = response.body().string();
        System.out.println(json);
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

    private String encodeValue(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
    }


}
