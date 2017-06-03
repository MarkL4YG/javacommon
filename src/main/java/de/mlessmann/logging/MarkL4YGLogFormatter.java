package de.mlessmann.logging;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.LogRecord;

/**
 * Created by Life4YourGames on 29.04.16.
 * @author Life4YourGames
 */
public class MarkL4YGLogFormatter extends java.util.logging.Formatter {

    public boolean isDebug = false;

    /**
     * Format a log message
     * @param lRec LogRecord
     * @see java.util.logging.Formatter#format
     * @return String
     */
    public String format(LogRecord lRec) {

        //No exception -> Standard formatting
        StringBuilder builder = new StringBuilder();

        builder.append(calcDate(lRec.getMillis()));
        builder.append(" ")
                .append(lRec.getLoggerName());
        builder.append(" ")
                .append(lRec.getLevel());
        builder.append(" ")
                .append(lRec.getMessage());
        builder.append("\n");

        populateThrowable(builder, lRec.getThrown(), 10);

        if (isDebug) {
            builder.append("--> Sent by:")
                    .append(lRec.getSourceClassName()).append(" :: ")
                    .append(lRec.getSourceMethodName())
                    .append("\n");
        }
        return builder.toString();
    }

    private void populateThrowable(StringBuilder b, Throwable t, int depth) {
        b.append("\nEXC ").append(t.getClass().getSimpleName()).append(' ').append(t.getLocalizedMessage());
        if (depth == 0) {
            b.append("  ...");
            return;
        }
        for (StackTraceElement ste : t.getStackTrace()) {
            b.append("\n  :").append(ste.getClassName()).append('#').append(ste.getMethodName())
                    .append(" (").append(ste.getFileName()).append(':').append(ste.getLineNumber());
        }
        for (Throwable sup : t.getSuppressed()) {
            b.append("\n  suppressed: ").append(sup.getClass().getSimpleName()).append(" -> ").append(sup.getMessage());
        }
        if (t.getCause() != null) {
            b.append("\ncaused by:");
            populateThrowable(b, t.getCause(), depth-1);
        }
    }

    private String calcDate(long milliSecs) {
        SimpleDateFormat date_format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date rDate = new Date(milliSecs);
        return date_format.format(rDate);
    }

    /**
     * Set if debug mode is enabled
     * @param b Whether or not debugging help is enabled
     * @return this
     */
    public MarkL4YGLogFormatter setDebug(boolean b){
        isDebug = b;
        return this;
    }

}
