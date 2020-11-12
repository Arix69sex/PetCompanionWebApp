package com.acme.petcompanion.resource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveServiceResource {

    @NotNull
    @Size(max = 25)
    private String title;

    @NotNull
    @Size(max = 250)
    private String description;

    @NotNull
    private float score;

    @NotNull
    @Size(max = 15)
    private String serviceType;

    @NotNull
    @Size(max = 55)
    private String address;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
