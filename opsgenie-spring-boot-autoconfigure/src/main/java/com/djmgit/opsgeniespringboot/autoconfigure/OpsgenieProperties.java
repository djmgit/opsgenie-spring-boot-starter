package com.djmgit.opsgeniespringboot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
@ConfigurationProperties(prefix = "opsgenie.springboot")
public class OpsgenieProperties {
    
    private String opsgenieApiKey = "";
    private String opsgenieApiBase = "";
    private String alertStatusCodes = "";
    private String alertStatusClasses = "";
    private String monitoredEndpoints = "";
    private String ignoredEndpoints = "";
    private String thresholdResponseTime = "";
    private String responseTimeMonitoredEndpoints = "";
    private String alertTags = "";
    private String alertDetails = "";
    private String alertPriority = "";
    private String alertAlias = "";
    private String alertStatusAlias = "";
    private String alertLatencyAlias = "";
    private String alertExceptionAlias = "";
    private String responder = "";
    private String serviceId = "";

    public String getOpsgenieApiKey() {
        return this.opsgenieApiKey;
    }

    public void setOpsgenieApiKey(String opsgenieApiKey) {
        this.opsgenieApiKey = opsgenieApiKey;
    }

    public String getOpsgenieApiBase() {
        return this.opsgenieApiBase;
    }

    public void setOpsgenieApiBase(String opsgenieApiBase) {
        this.opsgenieApiBase = opsgenieApiBase;
    }

    public String getAlertStatusCodes() {
        return this.alertStatusCodes;
    }

    public void setAlertStatusCodes(String alertStatusCodes) {
        this.alertStatusCodes = alertStatusCodes;
    }

    public String getAlertStatusClasses() {
        return this.alertStatusClasses;
    }

    public void setAlertStatusClasses(String alertStatusClasses) {
        this.alertStatusClasses = alertStatusClasses;
    }

    public String getMonitoredEndpoints() {
        return monitoredEndpoints;
    }

    public void setMonitoredEndpoints(String monitoredEndpoints) {
        this.monitoredEndpoints = monitoredEndpoints;
    }

    public String getIgnoredEndpoints() {
        return ignoredEndpoints;
    }

    public void setIgnoredEndpoints(String ignoredEndpoints) {
        this.ignoredEndpoints = ignoredEndpoints;
    }

    public String getThresholdResponseTime() {
        return thresholdResponseTime;
    }

    public void setThresholdResponseTime(String thresholdResponseTime) {
        this.thresholdResponseTime = thresholdResponseTime;
    }

    public String getResponseTimeMonitoredEndpoints() {
        return responseTimeMonitoredEndpoints;
    }

    public void setResponseTimeMonitoredEndpoints(String responseTimeMonitoredEndpoints) {
        this.responseTimeMonitoredEndpoints = responseTimeMonitoredEndpoints;
    }

    public String getAlertTags() {
        return alertTags;
    }

    public void setAlertTags(String alertTags) {
        this.alertTags = alertTags;
    }

    public String getAlertDetails() {
        return alertDetails;
    }

    public void setAlertDetails(String alertDetails) {
        this.alertDetails = alertDetails;
    }

    public String getAlertPriority() {
        return alertPriority;
    }

    public void setAlertPriority(String alertPriority) {
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

    public String getResponder() {
        return responder;
    }

    public void setResponder(String responder) {
        this.responder = responder;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
}
