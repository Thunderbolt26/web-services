package service;

import service.exceptions.ThrottlingException;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThrottlingLogic {
    private static AtomicInteger connectionCount = new AtomicInteger(0);
    private static final Integer MAX_CONNECTIONS_COUNT = 3;
    private static final Logger LOGGER = Logger.getLogger(ThrottlingLogic.class.getName());

    public static void addConnection() throws ThrottlingException {
        int count = connectionCount.incrementAndGet();
        if (count > MAX_CONNECTIONS_COUNT) {
            LOGGER.log(Level.INFO, "connection pull is full, count of connections:  " + count);
            throw ThrottlingException.DEFAULT_INSTANCE;
        }
        LOGGER.log(Level.INFO, "connection is added, count of connections: " + count);
    }

    public static void removeConnection() {
        int count = connectionCount.decrementAndGet();
        LOGGER.log(Level.INFO, "connection is realised, count of connections: " + count);
    }
}
