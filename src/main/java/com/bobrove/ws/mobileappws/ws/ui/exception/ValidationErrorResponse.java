package com.bobrove.ws.mobileappws.ws.ui.exception;

import com.bobrove.ws.mobileappws.ws.ui.model.response.ErrorMessage;

import java.util.List;

public class ValidationErrorResponse extends ErrorResponse {

    private final List<Violation> violations;

    public ValidationErrorResponse(String errorMessage) {
        super(errorMessage);
        violations = List.of();
    }

    public ValidationErrorResponse(ErrorMessage errorMessage, List<Violation> violations) {
        super(errorMessage.getErrorMessage());
        this.violations = violations;
    }

    public ValidationErrorResponse(String errorMessage, List<Violation> violations) {
        super(errorMessage);
        this.violations = violations;
    }

    public List<Violation> getViolations() {
        return violations;
    }

    public static class Violation {
        private final String fieldName;
        private final String message;

        public Violation(String fieldName, String message) {
            this.fieldName = fieldName;
            this.message = message;
        }

        public String getFieldName() {
            return fieldName;
        }

        public String getMessage() {
            return message;
        }
    }
}
