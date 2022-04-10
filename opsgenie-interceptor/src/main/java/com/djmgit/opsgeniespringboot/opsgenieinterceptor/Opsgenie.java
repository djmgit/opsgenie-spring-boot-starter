package com.djmgit.opsgeniespringboot.opsgenieinterceptor;

import static com.djmgit.opsgeniespringboot.opsgenieinterceptor.OpsgenieConfigParams.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import com.djmgit.opsgeniespringboot.opsgenieinterceptor.models.OpsgenieAlert;
import com.djmgit.opsgeniespringboot.opsgenieinterceptor.models.OpsgenieAlertType;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Opsgenie {
    
    private OpsgenieConfig opsgenieConfig;
    private ParsedOpsgenieConfig parsedOpsgenieConfig;

    public Opsgenie() {}

    public Opsgenie(OpsgenieConfig opsgenieConfig) {
        this.opsgenieConfig = opsgenieConfig;
        this.parsedOpsgenieConfig = ParsedOpsgenieConfig.parseRawConfig(opsgenieConfig);
    }

    public ParsedOpsgenieConfig getOpsgenieConfig() {
        return this.parsedOpsgenieConfig;
    }

    public String getResponseStatusClass(int statusCode) {
        return (statusCode / 100) + "XX";
    }

    public boolean isPathPresent(String endpoint, ArrayList<String> endpointPatterns) {
       return endpointPatterns.stream().filter(pattern -> Pattern.matches(pattern, endpoint)).findFirst().isPresent();
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

    public void raiseOpsgenieLatencyAlert(int elapsedTime, int alertStatusCode) {

        // implement response latency threshold alert
    }

    public void raiseOpsgenieAlert(OpsgenieAlertType alertType, int alertStatusCode) {

        // implement alert on response status code
    }

    public void raiseOpsgenieAlert(OpsgenieAlertType alertType, int alertStatusCode, String alertStatusClass) {

        // implement alert on response status class
    }

    public void raiseOpsgenieAlert(OpsgenieAlertType alertType, int alertStatusCode, int elapsedTime) {

        // implement alert on response threshold time
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
