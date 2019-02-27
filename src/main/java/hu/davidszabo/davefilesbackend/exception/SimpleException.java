package hu.davidszabo.davefilesbackend.exception;

import lombok.Getter;
import lombok.Setter;

public class SimpleException extends Exception {
    @Getter
    @Setter
    private int errorCode;
    @Getter
    @Setter
    private String errorMessage;
    @Getter
    @Setter
    private Object subject;

    public SimpleException(int errorCode, String errorMessage, Object subject) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.subject = subject;
    }
}
