package com.acme.petcompanion.resource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveReviewResource {

    @NotNull
    @Size(max = 25)
    private String title;

    @NotNull
    @Size(max = 250)
    private String description;

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
}
