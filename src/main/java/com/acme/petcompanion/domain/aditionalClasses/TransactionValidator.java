package com.acme.petcompanion.domain.aditionalClasses;

import com.acme.petcompanion.domain.model.Transacction;

import javax.validation.Validator;

public abstract class TransactionValidator implements Validator {
    public boolean test(Transacction t) {
        return checkDataIntegrity(t) && checkValidity(t);
    }

    public boolean checkValidity(Transacction t){
        return t.getPayer()!=null && t.getReciever()!=null;
    }

    public boolean checkDataIntegrity(Transacction t){
        return t.getAmount()>0;
    }

    public void updateAccounts(Transacction t){
        //Adds or removes money from the users accounts
    }
}
