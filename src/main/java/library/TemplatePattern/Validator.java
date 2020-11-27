package library.TemplatePattern;

import com.acme.petcompanion.domain.model.Transacction;

public class Validator {

    public Validator() {

    }

    public void test() {
        if (checkValidity()){
            if (checkDataIntegrity()) updateAccounts();
        }
    }

    public boolean checkValidity(){
        return false;
    }

    public boolean checkDataIntegrity(){
        return false;
    }

    public void updateAccounts(){
        //Adds or removes money from the users accounts
    }

}
