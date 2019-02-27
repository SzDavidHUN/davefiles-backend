package hu.davidszabo.davefilesbackend.exception;

public class FileCouldntBeImportedException extends SimpleException {

    public FileCouldntBeImportedException(int errorCode, String errorMessage, Object subject) {
        super(errorCode, errorMessage, subject);
    }
}
