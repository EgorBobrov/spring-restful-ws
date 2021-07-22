package com.bobrove.ws.mobileappws.ws.web.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDetailsRequestDto extends BaseUserRequestDto {

    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private String password;

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
}
