package service.exceptions;

public class DefaultException extends Exception {
    private static final long serialVersionUID = 5036119094557642747L;
    public static DefaultException DEFAULT_INSTANCE = new DefaultException("Unknown error");
    public DefaultException(String message) {
        super(message);
    }
}
