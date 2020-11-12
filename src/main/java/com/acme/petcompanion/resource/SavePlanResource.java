package com.acme.petcompanion.resource;

import org.springdoc.core.converters.models.MonetaryAmount;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SavePlanResource {
    @NotNull
    @Size(max = 25)
    private String name;

    @NotNull
    @Size(max = 150)
    private String description;

    @NotNull
    private MonetaryAmount price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MonetaryAmount getPrice() {
        return price;
    }

    public void setPrice(MonetaryAmount price) {
        this.price = price;
    }
}
