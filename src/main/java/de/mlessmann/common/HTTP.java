package de.mlessmann.common;

import de.mlessmann.common.annotations.Nullable;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by Life4YourGames on 09.09.16.
 */
public class HTTP {

    public static boolean GETFILE(String sUrl, File file) throws MalformedURLException, IOException {

        if (file.exists())
            file.delete();

        URL u = new URL(sUrl);

        ReadableByteChannel channel = Channels.newChannel(u.openStream());

        FileOutputStream out = new FileOutputStream(file);

        out.getChannel().transferFrom(channel, 0, Long.MAX_VALUE); //Results in a maximum size of 8 ExaBytes

        channel.close();

        out.close();

        return file.exists() && file.isFile();

    }

    public static String GET(String sUrl, @Nullable Proxy proxy) throws MalformedURLException, IOException {

        StringBuilder result = new StringBuilder();

        URL url = new URL(sUrl);

        HttpURLConnection connection;

        if (proxy != null)
            connection = (HttpURLConnection) url.openConnection(proxy);
        else
            connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String ln;
        while ((ln = reader.readLine()) != null) {
            result.append(ln);
            if (!ln.endsWith("\n"))
                result.append( "\n");
        }

        reader.close();

        return result.toString();

    }


}
