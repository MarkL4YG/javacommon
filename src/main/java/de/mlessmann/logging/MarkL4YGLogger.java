package de.mlessmann.logging;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Life4YourGames on 13.09.16.
 */
public class MarkL4YGLogger {

    private static Map<String, MarkL4YGLogger> loggerMap = new HashMap<String, MarkL4YGLogger>();

    public synchronized static MarkL4YGLogger get(String loggerID) {
        if (!loggerMap.containsKey(loggerID)) {
            loggerMap.put(loggerID, new MarkL4YGLogger(loggerID));
            return get(loggerID);
        }
        return loggerMap.get(loggerID);
    }

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

        logger.addHandler(stdHandler);
        logger.addHandler(errHandler);
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

    public MarkL4YGLogger setLogTrace(boolean debug) {
        logFormatter.setDebug(debug);
        return this;
    }

    public ILogReceiver getLogReceiver() {
        return new ILogReceiver() {
            @Override
            public void onMessage(Object sender, Level level, String message) {
                logger.log(level, sender.getClass().getSimpleName() + ' ' + message);
            }

            @Override
            public void onException(Object sender, Level level, Exception exception) {
                logger.log(level, sender.getClass().getSimpleName() + ' ' + exception.toString());
                exception.printStackTrace();
            }
        };
    }

}
