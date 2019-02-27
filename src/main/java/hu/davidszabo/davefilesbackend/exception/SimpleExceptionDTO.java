package hu.davidszabo.davefilesbackend.exception;

import lombok.Data;

@Data
public class SimpleExceptionDTO {
    private int errorCode;
    private String errorMessage;
    private Object subject;

    public SimpleExceptionDTO(SimpleException simpleException) {
        this.errorCode = simpleException.getErrorCode();
        this.errorMessage = simpleException.getErrorMessage();
        this.subject = simpleException.getSubject();
    }

    public SimpleExceptionDTO(int errorCode, String errorMessage, Object subject) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.subject = subject;
    }
}
