package com.djmgit.opsgeniespringboot.opsgenieinterceptor;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

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

    public void raiseOpsgenieStatusAlert(HttpServletRequest request, int alertStatusCode, String alertStatusClass) {

        String endpoint = request.getRequestURI();
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String summary = "";
        String description = "";

        HashMap<String, String> alertDetails = this.parsedOpsgenieConfig.getAlertDetails();

        alertDetails.put("endpoint", endpoint);
        alertDetails.put("url", url);
        alertDetails.put("method", method);

        if (alertStatusClass != "") {
            alertDetails.put("status_code", "" + alertStatusCode);
        } else {
            alertDetails.put("status_class", alertStatusClass);
        }

        OpsgenieAlert alertPayload = new OpsgenieAlert();
        String alias = this.parsedOpsgenieConfig.getServiceId() + "-" + endpoint + "-" + alertStatusCode;
        if (this.parsedOpsgenieConfig.getAlertStatusAlias() != "") {
            alias = alias + "-" + this.parsedOpsgenieConfig.getAlertStatusAlias();
        }

        if (alertStatusClass != "") {
            summary = String.format("%s returned unaccepted status class : %s | Alert generated from %s", endpoint, alertStatusClass, this.parsedOpsgenieConfig.getServiceId());
            description = String.format("%s returned status code from class : %s. Complete URL : %s called with method %s. Endpoint served by service : %s on host : %s",
                                         endpoint, alertStatusClass, url, method, this.parsedOpsgenieConfig.getServiceId(), this.parsedOpsgenieConfig.getHost());
        } else {
            summary = String.format("%s returned unaccepted status code : %d | Alert generated from %s", endpoint, alertStatusCode, this.parsedOpsgenieConfig.getServiceId());
            description = String.format("%s returned status : %d. Complete URL : %s called with method %s. Endpoint served by service : %s on host : %s",
                                         endpoint, alertStatusCode, url, method, this.parsedOpsgenieConfig.getServiceId(), this.parsedOpsgenieConfig.getHost());
        }

        alertPayload.setMessage(summary);
        alertPayload.setDescription(description);
        alertPayload.setAlias(alias);
        alertPayload.setTags(this.parsedOpsgenieConfig.getAlertTags());
        alertPayload.setDetails(alertDetails);
        alertPayload.setPriority(this.parsedOpsgenieConfig.getAlertPriority());

        if (this.parsedOpsgenieConfig.getResponders() != null) {
            alertPayload.setResponders(this.parsedOpsgenieConfig.getResponders());
        }

        try {
            this.makeOpsgenieApiRequest(String.format("%s/v2/alerts", this.parsedOpsgenieConfig.getOpsgenieApiBase()), this.parsedOpsgenieConfig.getOpsgenieApiKey(), alertPayload);
        } catch (Exception e) {}
    }

    public void raiseOpsgenieLatencyAlert(HttpServletRequest request, long elapsedTime, int alertStatusCode) {

        String endpoint = request.getRequestURI();
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String summary = "";
        String description = "";

        HashMap<String, String> alertDetails = this.parsedOpsgenieConfig.getAlertDetails();

        alertDetails.put("endpoint", endpoint);
        alertDetails.put("url", url);
        alertDetails.put("method", method);
        alertDetails.put("status_code", "" + alertStatusCode);

        OpsgenieAlert alertPayload = new OpsgenieAlert();
        String alias = this.parsedOpsgenieConfig.getServiceId() + "-" + endpoint + "-response-latency-alert";
        if (this.parsedOpsgenieConfig.getAlertLatencyAlias() != "") {
            alias = alias + "-" + this.parsedOpsgenieConfig.getAlertStatusAlias();
        }

        summary = String.format("%s showed unexpected response time : %dms | Alert generated from %s", endpoint, elapsedTime, this.parsedOpsgenieConfig.getServiceId());
        description = String.format("%s showed unexpected response time : %dms. Complete URL : %s called with method : %s. Endpoint served by service : %s on host : %s",
                                    endpoint, elapsedTime, url, method, this.parsedOpsgenieConfig.getServiceId());
        alertPayload.setMessage(summary);
        alertPayload.setDescription(description);
        alertPayload.setAlias(alias);
        alertPayload.setTags(this.parsedOpsgenieConfig.getAlertTags());
        alertPayload.setDetails(alertDetails);
        alertPayload.setPriority(this.parsedOpsgenieConfig.getAlertPriority());

        if (this.parsedOpsgenieConfig.getResponders() != null) {
            alertPayload.setResponders(this.parsedOpsgenieConfig.getResponders());
        }

        try {
            this.makeOpsgenieApiRequest(String.format("%s/v2/alerts", this.parsedOpsgenieConfig.getOpsgenieApiBase()), this.parsedOpsgenieConfig.getOpsgenieApiKey(), alertPayload);
        } catch (Exception e) {}
    }

    public void raiseOpsgenieAlert(HttpServletRequest request, OpsgenieAlertType alertType, int alertStatusCode) {

        if (alertType == OpsgenieAlertType.STATUS_ALERT) {
            this.raiseOpsgenieStatusAlert(request, alertStatusCode, "");
        }
    }

    public void raiseOpsgenieAlert(HttpServletRequest request, OpsgenieAlertType alertType, int alertStatusCode, String alertStatusClass) {

        if (alertType == OpsgenieAlertType.STATUS_ALERT) {
            this.raiseOpsgenieStatusAlert(request, alertStatusCode, alertStatusClass);
        }
    }

    public void raiseOpsgenieAlert(HttpServletRequest request, OpsgenieAlertType alertType, int alertStatusCode, long elapsedTime) {

        if (alertType == OpsgenieAlertType.LATENCY_ALERT) {
            this.raiseOpsgenieLatencyAlert(request, elapsedTime, alertStatusCode);
        }
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
