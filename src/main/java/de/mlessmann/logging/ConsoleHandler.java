package de.mlessmann.logging;

import java.io.PrintStream;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

/**
 * Created by Life4YourGames on 29.04.16.
 * @author Life4YourGames
 */
public class ConsoleHandler extends StreamHandler {

    private boolean enabled = true;

    /**
     * Create a new console Handler using out as OutputStream
     * @param outStream The outputStream that should be used
     */
    public ConsoleHandler(PrintStream outStream) {

        super();

        //Use default as default
        if (outStream == null) outStream = System.out;
        setOutputStream(outStream);
    }

    /**
     * Publish a log record
     * @param lRec the logRecord to public, null is silently ignored
     */
    @Override
    public void publish(LogRecord lRec) {
        if (enabled) {
            super.publish(lRec);
            flush();
        }
    }

    public void disable() {
        enabled = false;
    }

    /**
     * Close will flush() the stream, but will not close it's outStream
     * (We don't want to close System.out/System.err)
     */
    @Override
    public void close() {
        if (enabled) {
            flush();
        }
    }

}
