package service;

import service.exceptions.FootballClubServiceFault;
import service.exceptions.FormatException;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Validator {
    public final static String ERROR_NULL_ARG = "Null value for {0} is not allowed";

    public static void NotNull(Object s, String field) throws FormatException {
        if (s == null) {
            String msg = MessageFormat.format(ERROR_NULL_ARG, field);
            Logger.getLogger(Validator.class.getName()).log(Level.SEVERE, msg);
            FootballClubServiceFault fault = FootballClubServiceFault.getInstance(msg);
            throw new FormatException(msg, fault);
        }
    }
}
