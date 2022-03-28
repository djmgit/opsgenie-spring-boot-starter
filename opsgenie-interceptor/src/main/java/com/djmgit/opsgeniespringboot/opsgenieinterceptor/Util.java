package com.djmgit.opsgeniespringboot.opsgenieinterceptor;

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
}
