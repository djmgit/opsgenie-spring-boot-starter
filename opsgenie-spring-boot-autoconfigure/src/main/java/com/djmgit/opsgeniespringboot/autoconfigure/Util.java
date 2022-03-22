package com.djmgit.opsgeniespringboot.autoconfigure;

import com.djmgit.opsgeniespringboot.opsgenieinterceptor.OpsgenieConfig;
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
        String opsgenieApiBase = this.opsgenieProperties.getOpsgenieApiBase() == null ? "https://api.opsgenie.com" : this.opsgenieProperties.getOpsgenieApiBase();
        stringToArraylistTransformerPopulator(opsgenieConfig, this.opsgenieProperties.getAlertStatusCodes(), OPSGENIE_ALERT_STATUS_CODES);
        stringToArraylistTransformerPopulator(opsgenieConfig, this.opsgenieProperties.getAlertStatusClasses(), OPSGENIE_ALERT_STATUS_CLASSES);
        return opsgenieConfig;
    }
}
