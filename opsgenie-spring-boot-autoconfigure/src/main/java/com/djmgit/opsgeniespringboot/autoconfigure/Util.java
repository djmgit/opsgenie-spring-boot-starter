package com.djmgit.opsgeniespringboot.autoconfigure;

import com.djmgit.opsgeniespringboot.opsgenieinterceptor.OpsgenieConfig;
import com.djmgit.opsgeniespringboot.opsgenieinterceptor.models.OpsgenieAlertPriorities;

import static com.djmgit.opsgeniespringboot.opsgenieinterceptor.OpsgenieConfigParams.*;

import java.util.ArrayList;
import java.util.Arrays;

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

    private void stringToArraylistTransformerPopulator(OpsgenieConfig opsgenieConfig, String propertyValString, String propertyKey) {
        if (propertyValString == null) {
            return;
        }

        ArrayList<String> propertyValList = new ArrayList<String>(Arrays.asList(propertyValString.trim().split(",")));
        opsgenieConfig.put(propertyKey, propertyValList);
    }

    public OpsgenieConfig getOpsgeneiConfig() {
        OpsgenieConfig opsgenieConfig = new OpsgenieConfig();

        String opsgenieApiKey = this.opsgenieProperties.getOpsgenieApiKey() == null ? System.getenv(OPSGENIE_API_KEY) : this.opsgenieProperties.getOpsgenieApiKey();
        opsgenieConfig.put(OPSGENIE_API_KEY, opsgenieApiKey);
        String opsgenieApiBase = this.opsgenieProperties.getOpsgenieApiBase() == null ? "https://api.opsgenie.com" : this.opsgenieProperties.getOpsgenieApiBase();
        opsgenieConfig.put(OPSGENIE_API_BASE, opsgenieApiBase);
        this.stringToArraylistTransformerPopulator(opsgenieConfig, this.opsgenieProperties.getAlertStatusCodes(), OPSGENIE_ALERT_STATUS_CODES);
        this.stringToArraylistTransformerPopulator(opsgenieConfig, this.opsgenieProperties.getAlertStatusClasses(), OPSGENIE_ALERT_STATUS_CLASSES);
        this.stringToArraylistTransformerPopulator(opsgenieConfig, this.opsgenieProperties.getMonitoredEndpoints(), OPSGENIE_MONITORED_ENDPOINTS);
        this.stringToArraylistTransformerPopulator(opsgenieConfig, this.opsgenieProperties.getIgnoredEndpoints(), OPSGENIE_IGNORED_ENDPOINTS);
        opsgenieConfig.put(OPSGENIE_THRESHOLD_RESPONSE_TIME ,this.opsgenieProperties.getThresholdResponseTime());
        this.stringToArraylistTransformerPopulator(opsgenieConfig, this.opsgenieProperties.getResponseTimeMonitoredEndpoints(), OPSGENIE_RESPONSE_TIME_MONITORED_ENDPOINTS);
        this.stringToArraylistTransformerPopulator(opsgenieConfig, this.opsgenieProperties.getAlertTags(), OPSGENIE_ALERT_TAGS);
        opsgenieConfig.put(OPSGENIE_ALERT_PRIORITY, OpsgenieAlertPriorities.valueOf(this.opsgenieProperties.getAlertPriority()));
        opsgenieConfig.put(OPSGENIE_ALERT_ALIAS, this.opsgenieProperties.getAlertAlias());
        opsgenieConfig.put(OPSGENIE_ALERT_STATUS_ALIAS, this.opsgenieProperties.getAlertAlias());
        opsgenieConfig.put(OPSGENIE_ALERT_LATENCY_ALIAS, this.opsgenieProperties.getAlertLatencyAlias());
        opsgenieConfig.put(OPSGENIE_ALERT_EXCEPTION_ALIAS, this.opsgenieProperties.getAlertExceptionAlias());
        opsgenieConfig.put(OPSGENIE_SERVICE_ID, this.opsgenieProperties.getServiceId());

        return opsgenieConfig;
    }
}
