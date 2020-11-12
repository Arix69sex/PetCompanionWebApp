package com.acme.petcompanion.resource;

import org.springdoc.core.converters.models.MonetaryAmount;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class SaveTransacctionResource {

    @NotNull
    private float amount;

    @NotNull
    private Date creationDate;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
