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

        if (lRec.getThrown() == null) {
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
            //if (lRec.getThrown() != null) {
            //    builder.append(lRec.getThrown().toString());
            //}
            if (isDebug) {
                builder.append("--> Sent by:")
                        .append(lRec.getSourceClassName()).append(" :: ")
                        .append(lRec.getSourceMethodName())
                        .append("\n");
            }
            return builder.toString();

        } else {
            //Fall back to standard error format
            return lRec.getMessage();
            //return super.formatMessage(lRec);
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
