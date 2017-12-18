package de.mlessmann.logging;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

/**
 * Created by MarkL4YG on 18-Dec-17
 */
public class FileHandler extends StreamHandler{

    private boolean enabled = false;
    private boolean autoRotate = true;
    private File logFile = null;

    /**
     * Create a new console Handler using out as OutputStream
     * @param logFile the file to log to
     */
    public FileHandler(File logFile, boolean autoRotate) {
        super();
        setOutputStream(System.out);

        //Set default
        if (logFile == null) logFile = new File("log.txt");
    }

    /**
     * Open the output stream to enable logging.
     * @throws IOException
     */
    public void open() throws IOException {
        enabled = false;
        if (autoRotate && logFile.exists()) {
            Long lastModified = logFile.lastModified();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH-mm-ss");
            String fileName = format.format(lastModified) + ' ' + logFile.getName();

            File nextFile = null;
            int i = 0;
            do {
                nextFile = new File(fileName + '.' + i);
            } while (nextFile.exists());

            if (!logFile.renameTo(nextFile)) {
                throw new IOException("Failed to rotate log file!");
            }
        }

        setOutputStream(new FileOutputStream(logFile));
        enabled = true;
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
