package de.mlessmann.logging;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.LogRecord;

/**
 * Created by Life4YourGames on 29.04.16.
 * @author Life4YourGames
 */
public class LogFormatter extends java.util.logging.Formatter {

    public boolean isDebug = false;
    private static final Object lock = new Object();
    private static AtomicInteger levelDepth = new AtomicInteger(3);

    /**
     * Format a log message
     * @param lRec LogRecord
     * @see java.util.logging.Formatter#format
     * @return String
     */
    public String format(LogRecord lRec) {

        //No exception -> Standard formatting
        StringBuilder builder = new StringBuilder(128);

        builder.append(calcDate(lRec.getMillis()));
        builder.append(" ")
                .append(lRec.getLoggerName());

        // Try to level out the log-level sections in the log
        // Makes the log more table-like
        String level = lRec.getLevel().getLocalizedName();
        Integer currentDepth = levelDepth.get();
        if (currentDepth > level.length()) {
            // We need to add spaces
            char[] chars = level.toCharArray();
            chars = Arrays.copyOf(chars, currentDepth);
            Arrays.fill(chars, level.length(), chars.length, ' ');
            level = new String(chars);
        } else if (currentDepth < level.length()) {
            // We need to set the depth deeper
            levelDepth.set(level.length());
        }

        builder.append(" ")
                .append(level);
        builder.append(" ")
                .append(lRec.getMessage());

        if (lRec.getThrown() != null) {
            builder.append('\n').append(calcDate(lRec.getMillis())).append(' ');
            populateThrowable(builder, lRec.getThrown(), 10);
        }
        builder.append("\n");

        if (isDebug) {
            builder.append("--> ")
                    .append(lRec.getSourceClassName()).append(" :: ")
                    .append(lRec.getSourceMethodName())
                    .append("\n");
        }
        return builder.toString();
    }

    private void populateThrowable(StringBuilder b, Throwable t, int depth) {
        b.append("Logged exception in thread [").append(Thread.currentThread().getName()).append("] ").append(t.getClass().getName()).append(": ").append(t.getLocalizedMessage());
        if (depth == 0) {
            b.append("\n\t...");
            return;
        }
        for (StackTraceElement ste : t.getStackTrace()) {
            b.append("\n\tat ")
                    .append(ste.getClassName()).append('.')
                    .append(ste.getMethodName())
                    .append('(').append(ste.getFileName()).append(':').append(ste.getLineNumber()).append(')');
        }
        for (Throwable sup : t.getSuppressed()) {
            b.append("\n\tSuppressed: ").append(sup.getClass().getSimpleName()).append(" -> ").append(sup.getMessage());
        }
        if (t.getCause() != null) {
            b.append("\nCaused by:\n");
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
    public LogFormatter setDebug(boolean b){
        isDebug = b;
        return this;
    }

}
