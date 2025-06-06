package dyhb.cv.auth.exceptions;

public class EmailNotFoundException extends Exception {
    public EmailNotFoundException(String email) {
        super(String.format("Email %s already exists!", email));
    }
}
