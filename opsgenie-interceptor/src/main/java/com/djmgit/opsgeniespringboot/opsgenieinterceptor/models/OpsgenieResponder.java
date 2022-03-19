package com.djmgit.opsgeniespringboot.opsgenieinterceptor.models;

public class OpsgenieResponder {
    private String id;
    private String type;

    public OpsgenieResponder() {}

    public OpsgenieResponder(String id, OpsgenieResponderTypes responderType) {
        this.id = id;
        this.type = responderType.toString();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setType(OpsgenieResponderTypes responderType) {
        this.type = responderType.toString();
    }

    public String getType() {
        return this.type;
    }
}
