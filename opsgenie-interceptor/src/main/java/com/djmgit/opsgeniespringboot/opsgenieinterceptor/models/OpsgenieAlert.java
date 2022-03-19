package com.djmgit.opsgeniespringboot.opsgenieinterceptor.models;

import java.util.ArrayList;
import java.util.HashMap;


public class OpsgenieAlert {
    private String message = "";
    private String description = "";
    private String alias = "";
    private ArrayList<OpsgenieResponder> responders;
    private ArrayList<String> tags;
    private HashMap<String, String> details;
    private String priority = OpsgenieAlertPriorities.P3.toString();

    public OpsgenieAlert() {}

    public OpsgenieAlert(String message, String description, String alias, ArrayList<OpsgenieResponder> responders,
        ArrayList<String> tags, HashMap<String, String> details, OpsgenieAlertPriorities priority) {
        
        this.message = message;
        this.description = description;
        this.alias = alias;
        this.responders = responders;
        this.tags = tags;
        this.details = details;
        this.priority = priority.toString();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setResponders(ArrayList<OpsgenieResponder> responders) {
        this.responders = responders;
    }

    public ArrayList<OpsgenieResponder> getResponders() {
        return this.responders;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public ArrayList<String> getTags() {
        return this.tags;
    }

    public void setDetails(HashMap<String, String> details) {
        this.details = details;
    }

    public HashMap<String, String> getDetails() {
        return this.details;
    }

    public void setPriority(OpsgenieAlertPriorities priority) {
        this.priority = priority.toString();
    }

    public String getPriority() {
        return this.priority;
    }

}
