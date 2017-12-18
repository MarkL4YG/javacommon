package de.mlessmann.logging;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.StreamHandler;

/**
 * Created by Life4YourGames on 13.09.16.
 */
public class LogWrapper {

    private static Map<String, LogWrapper> loggerMap = new HashMap<String, LogWrapper>();

    public synchronized static LogWrapper get(String loggerID) {
        if (!loggerMap.containsKey(loggerID)) {
            loggerMap.put(loggerID, new LogWrapper(loggerID));
            return get(loggerID);
        }
        return loggerMap.get(loggerID);
    }

    private java.util.logging.Logger logger;
    private ConsoleHandler stdHandler;
    private ConsoleHandler errHandler;
    private LogFormatter logFormatter;
    private Receiver r = this.new Receiver();

    public LogWrapper(String loggerID) {
        logger = java.util.logging.Logger.getLogger(loggerID);
        logger.setUseParentHandlers(false);

        logFormatter = new LogFormatter();

        stdHandler = new ConsoleHandler(System.out);
        stdHandler.setFormatter(logFormatter);
        errHandler = new ConsoleHandler(System.err);
        errHandler.setFormatter(logFormatter);

        logger.addHandler(stdHandler);
        logger.addHandler(errHandler);
    }

    public void disableErrOut() {
        errHandler.disable();
    }

    public java.util.logging.Logger getLogger() {
        return logger;
    }

    public LogWrapper setLevel(Level level) {
        logger.setLevel(level);
        stdHandler.setLevel(level);
        errHandler.setLevel(level);

        if (level == Level.FINE || level == Level.FINER || level == Level.FINEST)
            logFormatter.setDebug(true);
        else
            logFormatter.setDebug(false);
        return this;
    }

    public LogWrapper setLogTrace(boolean debug) {
        logFormatter.setDebug(debug);
        return this;
    }

    public ILogReceiver getLogReceiver() {
        return r;
    }

    public ConsoleHandler getStdHandler() {
        return stdHandler;
    }

    public ConsoleHandler getErrHandler() {
        return errHandler;
    }

    /**
     * Add a handler
     * @param handler for example a {@link FileHandler}
     */
    public void addHandler(StreamHandler handler) {
        logger.addHandler(handler);
    }

    public void removeHandler(StreamHandler handler) {
        logger.removeHandler(handler);
    }

    public class Receiver implements ILogReceiver {

        @Override
        public void log(Level level, Object... message) {
            StringBuilder b = new StringBuilder();
            Throwable t = null;
            for (Object o : message) {
                if (o == null)
                    ;
                else if (o instanceof Throwable)
                    t = ((Throwable) o);
                else if (o instanceof String)
                    b.append(((String) o));
                else
                    b.append(o.toString());
            }
            logger.log(level, b.toString(), t);
        }

        @Override
        public void finest(Object... message) {
            log(Level.FINEST, message);
        }

        @Override
        public void finer(Object... message) {
            log(Level.FINER, message);
        }

        @Override
        public void fine(Object... message) {
            log(Level.FINE, message);
        }

        @Override
        public void info(Object... message) {
            log(Level.INFO, message);
        }

        @Override
        public void warning(Object... message) {
            log(Level.WARNING, message);
        }

        @Override
        public void severe(Object... message) {
            log(Level.SEVERE, message);
        }

        @Override
        public ILogReceiver getChild(String childPrefix) {
            return new ChildReceiver(childPrefix, this);
        }
    }

    public static class ChildReceiver implements ILogReceiver {

        private ILogReceiver parent;
        private String prefix;

        public ChildReceiver(String prefix, ILogReceiver parent) {
            this.prefix = prefix;
            this.parent = parent;
        }

        @Override
        public void log(Level level, Object... message) {
            Object[] nArr = new Object[message.length+2];
            for (int i = 0; i < message.length; i++) {
                nArr[i+2] = message[i];
            }
            nArr[0] = prefix;
            nArr[1] = ' ';
            parent.log(level, nArr);
        }

        @Override
        public void finest(Object... message) {
            log(Level.FINEST, message);
        }

        @Override
        public void finer(Object... message) {
            log(Level.FINER, message);
        }

        @Override
        public void fine(Object... message) {
            log(Level.FINE, message);
        }

        @Override
        public void info(Object... message) {
            log(Level.INFO, message);
        }

        @Override
        public void warning(Object... message) {
            log(Level.WARNING, message);
        }

        @Override
        public void severe(Object... message) {
            log(Level.SEVERE, message);
        }

        @Override
        public ILogReceiver getChild(String childPrefix) {
            return new ChildReceiver(childPrefix, this);
        }
    }
}
