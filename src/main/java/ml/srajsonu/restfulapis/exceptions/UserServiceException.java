package ml.srajsonu.restfulapis.exceptions;

public class UserServiceException extends RuntimeException {
    public UserServiceException(String message) {
        super(message);
    }
}
