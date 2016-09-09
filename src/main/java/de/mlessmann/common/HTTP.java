package de.mlessmann.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by Life4YourGames on 09.09.16.
 */
public class HTTP {

    public static boolean GETFILE(String sUrl, File file) throws MalformedURLException, IOException {

        URL u = new URL(sUrl);

        ReadableByteChannel channel = Channels.newChannel(u.openStream());

        FileOutputStream out = new FileOutputStream(file);

        out.getChannel().transferFrom(channel, 0, Long.MAX_VALUE); //Results in a maximum size of 8 ExaBytes

        channel.close();

        out.close();

        return file.exists() && file.isFile();

    }

}
