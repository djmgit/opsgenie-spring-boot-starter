package com.djmgit.opsgeniespringboot.opsgenieinterceptor;

import com.djmgit.opsgeniespringboot.opsgenieinterceptor.OpsgenieConfig;
import com.djmgit.opsgeniespringboot.opsgenieinterceptor.models.OpsgenieAlertPriorities;
import com.djmgit.opsgeniespringboot.opsgenieinterceptor.models.OpsgenieResponder;
import com.djmgit.opsgeniespringboot.opsgenieinterceptor.models.OpsgenieResponderTypes;

import static com.djmgit.opsgeniespringboot.opsgenieinterceptor.OpsgenieConfigParams.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ParsedOpsgenieConfig {
    
    /*
    private OpsgenieConfig opsgenieConfig;

    public OpsgenieConfig getOpsgenieConfig() {
        return this.opsgenieConfig;
    }

    public void setOpsgenieConfig(OpsgenieConfig opsgenieConfig) {
        this.opsgenieConfig = opsgenieConfig;
    }

    public Util() {}

    public Util(OpsgenieConfig opsgenieConfig) {
        this.opsgenieConfig = opsgenieConfig;
    }
    */

    private String opsgenieApiKey;
    private String opsgenieApiBase;
    private ArrayList<Integer> alertStatusCodes;
    private ArrayList<String> alertStatusClasses;
    private ArrayList<String> monitoredEndpoints;
    private ArrayList<String> ignoredEndpoints;
    private int thresholdResponseTime;
    private ArrayList<String> responseTimeMonitoredEndpoints;
    private ArrayList<String> alertTags;
    private HashMap<String, String> alertDetails;
    private OpsgenieAlertPriorities alertPriority;
    private String alertAlias;
    private String alertStatusAlias;
    private String alertLatencyAlias;
    private String alertExceptionAlias;
    private ArrayList<OpsgenieResponder> responders;
    private String serviceId; 

    public String getOpsgenieApiKey() {
        return opsgenieApiKey;
    }

    public void setOpsgenieApiKey(String opsgenieApiKey) {
        this.opsgenieApiKey = opsgenieApiKey;
    }

    public String getOpsgenieApiBase() {
        return opsgenieApiBase;
    }

    public void setOpsgenieApiBase(String opsgenieApiBase) {
        this.opsgenieApiBase = opsgenieApiBase;
    }

    public ArrayList<Integer> getAlertStatusCodes() {
        return alertStatusCodes;
    }

    public void setAlertStatusCodes(ArrayList<Integer> alertStatusCodes) {
        this.alertStatusCodes = alertStatusCodes;
    }

    public ArrayList<String> getAlertStatusClasses() {
        return alertStatusClasses;
    }

    public void setAlertStatusClasses(ArrayList<String> alertStatusClasses) {
        this.alertStatusClasses = alertStatusClasses;
    }

    public ArrayList<String> getMonitoredEndpoints() {
        return monitoredEndpoints;
    }

    public void setMonitoredEndpoints(ArrayList<String> monitoredEndpoints) {
        this.monitoredEndpoints = monitoredEndpoints;
    }

    public ArrayList<String> getIgnoredEndpoints() {
        return ignoredEndpoints;
    }

    public void setIgnoredEndpoints(ArrayList<String> ignoredEndpoints) {
        this.ignoredEndpoints = ignoredEndpoints;
    }

    public int getThresholdResponseTime() {
        return thresholdResponseTime;
    }

    public void setThresholdResponseTime(int thresholdResponseTime) {
        this.thresholdResponseTime = thresholdResponseTime;
    }

    public ArrayList<String> getResponseTimeMonitoredEndpoints() {
        return responseTimeMonitoredEndpoints;
    }

    public void setResponseTimeMonitoredEndpoints(ArrayList<String> responseTimeMonitoredEndpoints) {
        this.responseTimeMonitoredEndpoints = responseTimeMonitoredEndpoints;
    }

    public ArrayList<String> getAlertTags() {
        return alertTags;
    }

    public void setAlertTags(ArrayList<String> alertTags) {
        this.alertTags = alertTags;
    }

    public HashMap<String, String> getAlertDetails() {
        return alertDetails;
    }

    public void setAlertDetails(HashMap<String, String> alertDetails) {
        this.alertDetails = alertDetails;
    }

    public OpsgenieAlertPriorities getAlertPriority() {
        return alertPriority;
    }

    public void setAlertPriority(OpsgenieAlertPriorities alertPriority) {
        this.alertPriority = alertPriority;
    }

    public String getAlertAlias() {
        return alertAlias;
    }

    public void setAlertAlias(String alertAlias) {
        this.alertAlias = alertAlias;
    }

    public String getAlertStatusAlias() {
        return alertStatusAlias;
    }

    public void setAlertStatusAlias(String alertStatusAlias) {
        this.alertStatusAlias = alertStatusAlias;
    }

    public String getAlertLatencyAlias() {
        return alertLatencyAlias;
    }

    public void setAlertLatencyAlias(String alertLatencyAlias) {
        this.alertLatencyAlias = alertLatencyAlias;
    }

    public String getAlertExceptionAlias() {
        return alertExceptionAlias;
    }

    public void setAlertExceptionAlias(String alertExceptionAlias) {
        this.alertExceptionAlias = alertExceptionAlias;
    }

    public ArrayList<OpsgenieResponder> getResponders() {
        return responders;
    }

    public void setResponders(ArrayList<OpsgenieResponder> responders) {
        this.responders = responders;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    private ArrayList<String> stringToArraylistTransformerPopulator(String propertyValString) {
        ArrayList<String> propertyValList = new ArrayList<String>(Arrays.asList(propertyValString.trim().split(",")).stream().map(obj -> obj.trim())
                                                                                                   .collect(Collectors.toList()));
        return propertyValList;
    }

    private HashMap<String, String> stringToHashMapTransformerPopulator(String propertyVaString) {
        HashMap<String, String> details = new HashMap<String,String>();
        Arrays.asList(propertyVaString.trim().split(",")).stream().map(obj -> obj.trim())
                                                         .collect(Collectors.toList())
                                                         .forEach((e) -> {
                                                             details.put(e.split(":")[0].trim(), e.split(":")[1].trim());
                                                         });
        return details;
    }

    private ArrayList<Integer> parseAlertStatusCodes(String propertyValString) {

        ArrayList<Integer> propertyValList = new ArrayList<Integer>(this.stringToArraylistTransformerPopulator(propertyValString).stream()
                                                                                                                            .map(obj -> Integer.parseInt(obj))
                                                                                                                            .collect(Collectors.toList()));
        return propertyValList;
    }

    private int parseThresholdResponseTime(String propertyValString) {
        return Integer.parseInt(propertyValString);
    }

    private OpsgenieAlertPriorities parseAlertPriority(String propertyValString) {

        try {
            return OpsgenieAlertPriorities.valueOf(propertyValString);
        } catch (IllegalArgumentException e) {
            return OpsgenieAlertPriorities.P4;
        }
    }

    private OpsgenieResponderTypes parseOpsgenieResponderType(String propertyValString) {

        try {
            return OpsgenieResponderTypes.valueOf(propertyValString);
        } catch (IllegalArgumentException e) {
            return OpsgenieResponderTypes.team;
        }
    }

    private ArrayList<OpsgenieResponder> parseOpsgenieAlertResponders(String propertyValString) {

        ArrayList<OpsgenieResponder> opsgenieResponders = new ArrayList<OpsgenieResponder>();
        this.stringToArraylistTransformerPopulator(propertyValString).forEach((e) -> {
            String responderId = e.split(":")[0].trim();
            OpsgenieResponderTypes responderType = this.parseOpsgenieResponderType(e.split(":")[1].trim());
            opsgenieResponders.add(new OpsgenieResponder(responderId, responderType));
        });

        return opsgenieResponders;
    }


    public static ParsedOpsgenieConfig parseRawConfig(OpsgenieConfig opsgenieConfig) {

        ParsedOpsgenieConfig parsedOpsgenieConfig = new ParsedOpsgenieConfig();

        parsedOpsgenieConfig.setOpsgenieApiKey(opsgenieConfig.getProperty(OPSGENIE_API_KEY));
        parsedOpsgenieConfig.setOpsgenieApiBase(opsgenieConfig.getProperty(OPSGENIE_API_BASE));
        parsedOpsgenieConfig.setAlertStatusCodes(parsedOpsgenieConfig.parseAlertStatusCodes(opsgenieConfig.getProperty(OPSGENIE_ALERT_STATUS_CODES)));
        parsedOpsgenieConfig.setAlertStatusClasses(parsedOpsgenieConfig.stringToArraylistTransformerPopulator(opsgenieConfig.getProperty(OPSGENIE_ALERT_STATUS_CLASSES)));
        parsedOpsgenieConfig.setMonitoredEndpoints(parsedOpsgenieConfig.stringToArraylistTransformerPopulator(opsgenieConfig.getProperty(OPSGENIE_MONITORED_ENDPOINTS)));
        parsedOpsgenieConfig.setIgnoredEndpoints(parsedOpsgenieConfig.stringToArraylistTransformerPopulator(opsgenieConfig.getProperty(OPSGENIE_IGNORED_ENDPOINTS)));
        parsedOpsgenieConfig.setThresholdResponseTime(parsedOpsgenieConfig.parseThresholdResponseTime(opsgenieConfig.getProperty((OPSGENIE_THRESHOLD_RESPONSE_TIME))));
        parsedOpsgenieConfig.setResponseTimeMonitoredEndpoints(parsedOpsgenieConfig.stringToArraylistTransformerPopulator(opsgenieConfig.getProperty(OPSGENIE_RESPONSE_TIME_MONITORED_ENDPOINTS)));
        parsedOpsgenieConfig.setAlertTags(parsedOpsgenieConfig.stringToArraylistTransformerPopulator(opsgenieConfig.getProperty(OPSGENIE_ALERT_TAGS)));
        parsedOpsgenieConfig.setAlertDetails(parsedOpsgenieConfig.stringToHashMapTransformerPopulator(opsgenieConfig.getProperty(OPSGENIE_ALERT_DETAILS)));
        parsedOpsgenieConfig.setAlertPriority(parsedOpsgenieConfig.parseAlertPriority(opsgenieConfig.getProperty(OPSGENIE_ALERT_PRIORITY)));
        parsedOpsgenieConfig.setAlertAlias(opsgenieConfig.getProperty(OPSGENIE_ALERT_ALIAS));
        parsedOpsgenieConfig.setAlertStatusAlias(opsgenieConfig.getProperty(OPSGENIE_ALERT_STATUS_ALIAS));
        parsedOpsgenieConfig.setAlertLatencyAlias(opsgenieConfig.getProperty(OPSGENIE_ALERT_LATENCY_ALIAS));
        parsedOpsgenieConfig.setAlertExceptionAlias(opsgenieConfig.getProperty(OPSGENIE_ALERT_EXCEPTION_ALIAS));
        parsedOpsgenieConfig.setResponders(parsedOpsgenieConfig.parseOpsgenieAlertResponders(opsgenieConfig.getProperty(OPSGENIE_RESPONDER)));
        parsedOpsgenieConfig.setServiceId(opsgenieConfig.getProperty(OPSGENIE_SERVICE_ID));

        return parsedOpsgenieConfig;
    }
}
