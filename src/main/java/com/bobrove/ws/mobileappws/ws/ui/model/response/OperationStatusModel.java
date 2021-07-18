package com.bobrove.ws.mobileappws.ws.ui.model.response;

public class OperationStatusModel {
    private final OperationStatus operationStatus;
    private final OperationName operationName;

    public OperationStatusModel(OperationStatus operationResult,
                                OperationName operationName) {
        this.operationStatus = operationResult;
        this.operationName = operationName;
    }

    public OperationStatus getOperationStatus() {
        return operationStatus;
    }

    public OperationName getOperationName() {
        return operationName;
    }

    public static enum OperationStatus {
        SUCCESS, FAILURE
    }

    public static enum OperationName {
        DELETE
    }
}
