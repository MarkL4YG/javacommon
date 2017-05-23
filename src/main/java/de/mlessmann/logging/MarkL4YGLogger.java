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
    private Receiver r = this.new Receiver();

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

    public void disableErrOut() {
        errHandler.disable();
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
        return r;
    }

    public class Receiver implements ILogReceiver {

        @Override
        public void log(Object sender, Level level, Object... message) {
            StringBuilder b = new StringBuilder();
            if (sender instanceof String) {
                b.append(sender);
            } else {
                b.append(sender.getClass().getSimpleName());
            }
            Throwable t = null;
            for (Object o : message) {
                if (o instanceof Throwable)
                    t = ((Throwable) o);
                else if (o instanceof String)
                    b.append(((String) o));
            }
            logger.log(level, b.toString(), t);
        }

        @Override
        public void finest(Object sender, Object... message) {
            log(sender, Level.FINEST, message);
        }

        @Override
        public void finer(Object sender, Object... message) {
            log(sender, Level.FINER, message);
        }

        @Override
        public void fine(Object sender, Object... message) {
            log(sender, Level.FINE, message);
        }

        @Override
        public void info(Object sender, Object... message) {
            log(sender, Level.INFO, message);
        }

        @Override
        public void warning(Object sender, Object... message) {
            log(sender, Level.WARNING, message);
        }

        @Override
        public void severe(Object sender, Object... message) {
            log(sender, Level.SEVERE, message);
        }
    }
}
