package com.djmgit.opsgeniespringboot.opsgenieinterceptor;

import com.djmgit.opsgeniespringboot.opsgenieinterceptor.models.OpsgenieAlertPriorities;
import com.djmgit.opsgeniespringboot.opsgenieinterceptor.models.OpsgenieResponder;
import com.djmgit.opsgeniespringboot.opsgenieinterceptor.models.OpsgenieResponderTypes;

import static com.djmgit.opsgeniespringboot.opsgenieinterceptor.OpsgenieConfigParams.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

    private String opsgenieApiKey = "";
    private String opsgenieApiBase = "";
    private ArrayList<Integer> alertStatusCodes = null;
    private ArrayList<String> alertStatusClasses = null;
    private ArrayList<String> monitoredEndpoints = null;
    private ArrayList<String> ignoredEndpoints = null;
    private int thresholdResponseTime = -1;
    private ArrayList<String> responseTimeMonitoredEndpoints = null;
    private ArrayList<String> alertTags = null;
    private HashMap<String, String> alertDetails = null;
    private OpsgenieAlertPriorities alertPriority = null;
    private String alertAlias = "";
    private String alertStatusAlias = "";
    private String alertLatencyAlias = "";
    private String alertExceptionAlias = "";
    private ArrayList<OpsgenieResponder> responders = null;
    private String serviceId = "";
    private String host = "";

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
        if (alertStatusAlias == "" || alertStatusAlias == null) {
            this.alertStatusAlias = this.alertAlias;
        } else {
            this.alertStatusAlias = alertStatusAlias;
        }
    }

    public String getAlertLatencyAlias() {
        return alertLatencyAlias;
    }

    public void setAlertLatencyAlias(String alertLatencyAlias) {
        if (alertLatencyAlias == "" || alertLatencyAlias == null) {
            this.alertLatencyAlias = this.alertAlias;
        } else {
            this.alertLatencyAlias = alertLatencyAlias;
        }
    }

    public String getAlertExceptionAlias() {
        return alertExceptionAlias;
    }

    public void setAlertExceptionAlias(String alertExceptionAlias) {
        if (alertExceptionAlias == "" || alertExceptionAlias == null) {
            this.alertExceptionAlias = this.alertAlias;
        } else {
            this.alertExceptionAlias = alertExceptionAlias;
        }
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
        if ((serviceId != "") && (serviceId != null)) {
            this.serviceId = serviceId;
        } else {
            this.serviceId = "Java-SpringBoot-Service";
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    private ArrayList<String> stringToArraylistTransformerPopulator(String propertyValString) {
        if (propertyValString == "" || propertyValString == null) {
            return null;
        }
        ArrayList<String> propertyValList = new ArrayList<String>(Arrays.asList(propertyValString.trim().split(",")).stream().map(obj -> obj.trim())
                                                                                                   .collect(Collectors.toList()));
        return propertyValList;
    }

    private HashMap<String, String> stringToHashMapTransformerPopulator(String propertyValString) {
        if (propertyValString == "" || propertyValString == null) {
            return null;
        }
        HashMap<String, String> details = new HashMap<String,String>();
        Arrays.asList(propertyValString.trim().split(",")).stream().map(obj -> obj.trim())
                                                         .collect(Collectors.toList())
                                                         .forEach((e) -> {
                                                             details.put(e.split(":")[0].trim(), e.split(":")[1].trim());
                                                         });
        return details;
    }

    private ArrayList<Integer> parseAlertStatusCodes(String propertyValString) {
        if (propertyValString == "" || propertyValString == null) {
            return null;
        }
        ArrayList<Integer> propertyValList = new ArrayList<Integer>(this.stringToArraylistTransformerPopulator(propertyValString).stream()
                                                                                                                            .map(obj -> Integer.parseInt(obj))
                                                                                                                            .collect(Collectors.toList()));
        return propertyValList;
    }

    private int parseThresholdResponseTime(String propertyValString) {
        try {
            return Integer.parseInt(propertyValString);
        } catch (NumberFormatException e) {
            return -1;
        }
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
        if (propertyValString == "" || propertyValString == null) {
            return null;
        }
        ArrayList<OpsgenieResponder> opsgenieResponders = new ArrayList<OpsgenieResponder>();
        this.stringToArraylistTransformerPopulator(propertyValString).forEach((e) -> {
            String responderId = e.split(":")[0].trim();
            OpsgenieResponderTypes responderType = this.parseOpsgenieResponderType(e.split(":")[1].trim());
            opsgenieResponders.add(new OpsgenieResponder(responderId, responderType));
        });

        return opsgenieResponders;
    }

    private String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "";
        }
    }

    private void populateDefaultDetails() {
        if (this.alertDetails == null) {
            this.alertDetails = new HashMap<String, String>();
        }

        // populate the default values if present
        if (this.host != "") {
            this.alertDetails.put("host", this.host);
        }

        if (this.serviceId != "") {
            this.alertDetails.put("service_id", this.serviceId);
        } else {
            this.alertDetails.put("service_id", "Java-SpringBoot-Service");
        }
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
        parsedOpsgenieConfig.setHost(parsedOpsgenieConfig.getHostName());
        parsedOpsgenieConfig.populateDefaultDetails();

        return parsedOpsgenieConfig;
    }
}
