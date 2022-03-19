package com.djmgit.opsgeniespringboot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
@ConfigurationProperties(prefix = "opsgenie.springboot")
public class OpsgenieProperties {
    
    private String opsgenieApiKey;
    private String opsgenieApiBase;
    private String alertStatusCodes;
    private String alertStatusClasses;
    private String monitoredEndpoints;
    private String ignoredEndpoints;
    private String thresholdResponseTime;
    private String responseTimeMonitoredEndpoints;
    private String alertTags;
    private String alertDetails;

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
}
