package com.bobrove.ws.mobileappws.ws.web.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public abstract class BaseUserRequestDto {

    @NotNull
    @NotEmpty
    private String firstName;
    @NotNull
    @NotEmpty
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
