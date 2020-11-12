package com.acme.petcompanion.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveUserResource {

    @NotNull
    @NotBlank
    @Size(max = 25)
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 25)
    private String password;

    @NotNull
    private boolean premium;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }
}
