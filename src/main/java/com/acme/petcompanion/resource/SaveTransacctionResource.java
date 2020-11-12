package com.acme.petcompanion.resource;

import org.springdoc.core.converters.models.MonetaryAmount;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class SaveTransacctionResource {

    @NotNull
    private MonetaryAmount amount;

    @NotNull
    private Date creationDate;

    public MonetaryAmount getAmount() {
        return amount;
    }

    public void setAmount(MonetaryAmount amount) {
        this.amount = amount;
    }
}
