package com.djmgit.opsgeniespringboot.autoconfigure;

import com.djmgit.opsgeniespringboot.opsgenieinterceptor.OpsgenieConfig;
import com.djmgit.opsgeniespringboot.opsgenieinterceptor.models.OpsgenieAlertPriorities;

import static com.djmgit.opsgeniespringboot.opsgenieinterceptor.OpsgenieConfigParams.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

        List<String> propertyValList = new ArrayList<String>(Arrays.asList(propertyValString.trim().split(",")).stream().map(obj -> obj.trim())
                                                                                                   .collect(Collectors.toList()));
        opsgenieConfig.put(propertyKey, propertyValList);
    }

    private void stringToHashMapTransformerPopulator(OpsgenieConfig opsgenieConfig, String propertyVaString, String propertyKey) {
        if (propertyVaString == null) {
            return;
        }

        HashMap<String, String> details = new HashMap<String,String>();
        Arrays.asList(propertyVaString.trim().split(",")).stream().map(obj -> obj.trim())
                                                         .collect(Collectors.toList())
                                                         .forEach((e) -> {
                                                             details.put(e.split(":")[0].trim(), e.split(":")[1].trim());
                                                         });
        opsgenieConfig.put(propertyKey, details);
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
        this.stringToHashMapTransformerPopulator(opsgenieConfig, this.opsgenieProperties.getAlertDetails(), OPSGENIE_ALERT_DETAILS);
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
