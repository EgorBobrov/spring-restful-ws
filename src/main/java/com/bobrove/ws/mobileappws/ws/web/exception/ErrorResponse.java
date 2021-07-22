package com.bobrove.ws.mobileappws.ws.web.exception;

import com.bobrove.ws.mobileappws.ws.web.model.response.ErrorMessage;

public class ErrorResponse {
    private final String errorMessage;

    public ErrorResponse(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage.getErrorMessage();
    }

    public ErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
