package service.exceptions;

public class DataNotFoundException extends Exception {
    private static final long serialVersionUID = -7046549738039047683L;
    public static FormatException DEFAULT_INSTANCE = new FormatException("Data not found");

    public DataNotFoundException(String message) {
        super(message);
    }
}