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

    public OpsgenieConfig getOpsgeneiConfig() {
        OpsgenieConfig opsgenieConfig = new OpsgenieConfig();

        String opsgenieApiKey = this.opsgenieProperties.getOpsgenieApiKey() == null ? System.getenv(OPSGENIE_API_KEY) : this.opsgenieProperties.getOpsgenieApiKey();
        String opsgenieApiBase = this.opsgenieProperties.getOpsgenieApiBase() == null ? "https://api.opsgenie.com" : this.opsgenieProperties.getOpsgenieApiBase();
        String alertStatusCodes = this.opsgenieProperties.getAlertStatusCodes();

        // transform alert status codes string into array array list of alert status codes
        if (alertStatusCodes != null) {
            ArrayList<String> alertStatusCodesList = new ArrayList<String>(Arrays.asList(alertStatusCodes.trim().split(",")));
            opsgenieConfig.put(OPSGENIE_ALERT_STATUS_CODES, alertStatusCodesList);
        }

        String alertStatusClasses = this.opsgenieProperties.getAlertStatusClasses();

        // trasnform alert status classes string into array list of classes
        if (alertStatusClasses != null) {
            ArrayList<String> alertStatusClassesList = new ArrayList<String>(Arrays.asList(alertStatusClasses.trim().split(",")));
            opsgenieConfig.put(OPSGENIE_ALERT_STATUS_CLASSES, alertStatusClassesList);
        }

        return opsgenieConfig;
    }
}
