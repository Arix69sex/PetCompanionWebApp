package com.acme.petcompanion.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveUserDataResource {

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String lastName;

    @NotNull
    private float scoreOwner;

    @NotNull
    private float scoreProvider;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public float getScoreOwner() {
        return scoreOwner;
    }

    public void setScoreOwner(float scoreOwner) {
        this.scoreOwner = scoreOwner;
    }

    public float getScoreProvider() {
        return scoreProvider;
    }

    public void setScoreProvider(float scoreProvider) {
        this.scoreProvider = scoreProvider;
    }
}
