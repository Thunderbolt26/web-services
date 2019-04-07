package service.exceptions;

public class FormatException extends Exception {
    private static final long serialVersionUID = -6239149565765321918L;
    public static FormatException DEFAULT_INSTANCE = new FormatException("Incorrect format");

    public FormatException(String message) {
        super(message);
    }
 }
