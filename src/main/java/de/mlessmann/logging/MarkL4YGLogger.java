package de.mlessmann.logging;

import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Life4YourGames on 13.09.16.
 */
public class MarkL4YGLogger {

    private Logger logger;
    private MarkL4YGConsoleHandler stdHandler;
    private MarkL4YGConsoleHandler errHandler;
    private MarkL4YGLogFormatter logFormatter;

    public MarkL4YGLogger(String loggerID) {
        logger = Logger.getLogger(loggerID);
        logger.setUseParentHandlers(false);

        logFormatter = new MarkL4YGLogFormatter();

        stdHandler = new MarkL4YGConsoleHandler(System.out);
        stdHandler.setFormatter(logFormatter);
        errHandler = new MarkL4YGConsoleHandler(System.err);
        errHandler.setFormatter(logFormatter);
    }

    public Logger getLogger() {
        return logger;
    }

    public MarkL4YGLogger setLevel(Level level) {
        logger.setLevel(level);
        stdHandler.setLevel(level);
        errHandler.setLevel(level);

        if (level == Level.FINE || level == Level.FINER || level == Level.FINEST)
            logFormatter.setDebug(true);
        else
            logFormatter.setDebug(false);
        return this;
    }

}
