package com.miDNA.andrew.controllers;

import com.miDNA.andrew.entities.PMIMEntry;
import com.miDNA.andrew.services.OMIMEntryService;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.*;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
@CrossOrigin
public class OMIMController {

   @Autowired
   private OMIMEntryService omimEntryService;

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

    @PostMapping(value = "/omim")
    public ArrayList<PMIMEntry> getPMIMs(@RequestParam(name = "iemOMIMs") String iemOMIMs) throws IOException {
        System.out.println("Recieved Params"+ iemOMIMs);
        ArrayList<String> omims = new ArrayList<>(Arrays.asList(iemOMIMs.split(",")));
        System.out.println("Processing " + omims.size() + " OMIMs");
        ArrayList<PMIMEntry> pmimEntries = new ArrayList<>();
        for (String omim : omims) {
            ArrayList<PMIMEntry> pmimEntry = getOMIMData(omim);
            if(!pmimEntry.isEmpty()) {
                pmimEntries.addAll(pmimEntry);
                System.out.println("Finished Processing OMIM no : " + omim);
            }
        }
        return pmimEntries;
    }

    @Transactional
    public ArrayList<PMIMEntry> getOMIMData(String omimNo) throws IOException {
        return omimEntryService.getAllEntriesByOMIMId(omimNo);
    }

    @GetMapping(value="/omim/load")
    public void loadOMIMDatabase() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("omim.txt"));
        String line="";
        while ((line=br.readLine())!=null)
        {
            System.out.print(line);
            // System.out.println(" --- LENGTH "+line.split("\\|").length+"\n");
            String fields [] = line.split("\\|");
            String pmim_name =  fields[0];
            String pmim_genes =  fields[1];
            String pmim_id = fields[2];
            String pmim_location = fields[3];
            String omimId = pmim_name.replaceAll(".+([0-9]{6}).+","$1");
            if (omimId.length() > 0)
            {
                System.out.println(omimId);
                PMIMEntry pmimEntry = new PMIMEntry();
                pmimEntry.setPmim_name(pmim_name);
                pmimEntry.setPmim_genes(pmim_genes);
                pmimEntry.setPmim_id(pmim_id);
                pmimEntry.setPmim_location(pmim_location);
                pmimEntry.setOmimId(omimId);
                omimEntryService.savePMIMEntry(pmimEntry);
            }
        }
        // pmim_name | pmim_genes | pmim_id | pmim_location
    }

}
