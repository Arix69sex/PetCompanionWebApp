package com.acme.petcompanion.resource;

public class UserDataResource {
    private Long id;
    private String name;
    private String lastName;
    private float scoreOwner;
    private float scoreProvider;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
