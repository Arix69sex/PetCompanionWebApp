package com.acme.petcompanion.resource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveReviewResource {

    @NotNull
    @Size(max = 25)
    private String title;

    @NotNull
    @Size(max = 250)
    private String text;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
