package com.djmgit.opsgeniespringboot.opsgenieinterceptor;

import static com.djmgit.opsgeniespringboot.opsgenieinterceptor.OpsgenieConfigParams.*;

import java.net.URI;
import java.net.URISyntaxException;

import com.djmgit.opsgeniespringboot.opsgenieinterceptor.models.OpsgenieAlert;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Opsgenie {
    
    private OpsgenieConfig opsgenieConfig;

    public Opsgenie() {}

    public Opsgenie(OpsgenieConfig opsgenieConfig) {
        this.opsgenieConfig = opsgenieConfig;
    }

    public void raiseOpsgenieStatusAlert(String alertStatusCode, String alertStatusClass) {

        OpsgenieAlert alertPayload = new OpsgenieAlert();
        alertPayload.setMessage("This is a test alert");
        alertPayload.setDescription("This is a test descritiption");
        String opsgenieApiKey = this.opsgenieConfig.get(OPSGENIE_API_KEY).toString();
        String opsgenieAlertEndpoint = String.format("%s/v2/alerts", this.opsgenieConfig.get(OPSGENIE_API_BASE));

        ResponseEntity<String> response = null;
        try {
            response = this.makeOpsgenieApiRequest(opsgenieAlertEndpoint, opsgenieApiKey, alertPayload);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        System.out.println(response);

    }

    private HttpHeaders getOpsgenieRequestHeaders(String opsgenieApiKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "GenieKey " + opsgenieApiKey);

        return headers;
    }

    private ResponseEntity<String> makeOpsgenieApiRequest(String opsgenieAlertEndpoint, String opsgenieApiKey, OpsgenieAlert alertPayload) throws URISyntaxException {

        RestTemplate restTemplate = new RestTemplate();
        URI opsgenieUri = new URI(opsgenieAlertEndpoint);

        HttpHeaders opsgenieRequestHeaders = this.getOpsgenieRequestHeaders(opsgenieApiKey);
        HttpEntity<OpsgenieAlert> opsgenieApiRequest = new HttpEntity<OpsgenieAlert>(alertPayload, opsgenieRequestHeaders);        
        ResponseEntity<String> result = restTemplate.postForEntity(opsgenieUri, opsgenieApiRequest, String.class);

        return result;
    }
    
}
