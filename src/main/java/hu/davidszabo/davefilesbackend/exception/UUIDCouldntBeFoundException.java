package hu.davidszabo.davefilesbackend.exception;

public class UUIDCouldntBeFoundException extends SimpleException {
    public UUIDCouldntBeFoundException(int errorCode, String errorMessage, Object subject) {
        super(errorCode, errorMessage, subject);
    }
}
