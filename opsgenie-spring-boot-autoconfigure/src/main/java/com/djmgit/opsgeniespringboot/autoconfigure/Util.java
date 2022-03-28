package com.djmgit.opsgeniespringboot.autoconfigure;

import com.djmgit.opsgeniespringboot.opsgenieinterceptor.OpsgenieConfig;
import static com.djmgit.opsgeniespringboot.opsgenieinterceptor.OpsgenieConfigParams.*;
public class Util {
    
    private OpsgenieProperties opsgenieProperties;

    public OpsgenieProperties getOpsgenieProperties() {
        return opsgenieProperties;
    }

    public void setOpsgenieProperties(OpsgenieProperties opsgenieProperties) {
        this.opsgenieProperties = opsgenieProperties;
    }

    public Util() {}

    public Util(OpsgenieProperties opsgenieProperties) {
        this.opsgenieProperties = opsgenieProperties;
    }

    public OpsgenieConfig getOpsgeneiConfig() {
        OpsgenieConfig opsgenieConfig = new OpsgenieConfig();

        String opsgenieApiKey = this.opsgenieProperties.getOpsgenieApiKey() == null ? System.getenv(OPSGENIE_API_KEY) : this.opsgenieProperties.getOpsgenieApiKey();
        opsgenieConfig.put(OPSGENIE_API_KEY, opsgenieApiKey);
        String opsgenieApiBase = this.opsgenieProperties.getOpsgenieApiBase() == null ? "https://api.opsgenie.com" : this.opsgenieProperties.getOpsgenieApiBase();
        opsgenieConfig.put(OPSGENIE_API_BASE, opsgenieApiBase);
        opsgenieConfig.put(OPSGENIE_ALERT_STATUS_CODES, this.opsgenieProperties.getAlertStatusCodes());
        opsgenieConfig.put(OPSGENIE_ALERT_STATUS_CLASSES, this.opsgenieProperties.getAlertStatusClasses());
        opsgenieConfig.put(OPSGENIE_MONITORED_ENDPOINTS, this.opsgenieProperties.getMonitoredEndpoints());
        opsgenieConfig.put(OPSGENIE_IGNORED_ENDPOINTS, this.opsgenieProperties.getIgnoredEndpoints());
        opsgenieConfig.put(OPSGENIE_ALERT_DETAILS, this.opsgenieProperties.getAlertDetails());
        opsgenieConfig.put(OPSGENIE_RESPONDER, this.opsgenieProperties.getResponder());
        opsgenieConfig.put(OPSGENIE_THRESHOLD_RESPONSE_TIME ,this.opsgenieProperties.getThresholdResponseTime());
        opsgenieConfig.put(OPSGENIE_RESPONSE_TIME_MONITORED_ENDPOINTS, this.opsgenieProperties.getResponseTimeMonitoredEndpoints());
        opsgenieConfig.put(OPSGENIE_ALERT_TAGS, this.opsgenieProperties.getAlertTags());
        opsgenieConfig.put(OPSGENIE_ALERT_PRIORITY, this.opsgenieProperties.getAlertPriority());
        opsgenieConfig.put(OPSGENIE_ALERT_ALIAS, this.opsgenieProperties.getAlertAlias());
        opsgenieConfig.put(OPSGENIE_ALERT_STATUS_ALIAS, this.opsgenieProperties.getAlertAlias());
        opsgenieConfig.put(OPSGENIE_ALERT_LATENCY_ALIAS, this.opsgenieProperties.getAlertLatencyAlias());
        opsgenieConfig.put(OPSGENIE_ALERT_EXCEPTION_ALIAS, this.opsgenieProperties.getAlertExceptionAlias());
        opsgenieConfig.put(OPSGENIE_SERVICE_ID, this.opsgenieProperties.getServiceId());

        return opsgenieConfig;
    }
}
