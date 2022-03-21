package com.djmgit.opsgeniespringboot.autoconfigure;

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
}
