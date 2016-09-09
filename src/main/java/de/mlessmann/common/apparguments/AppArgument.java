package de.mlessmann.common.apparguments;

import java.util.ArrayList;

/**
 * Created by Life4YourGames on 06.07.16.
 */

public class AppArgument {

    public static ArrayList<AppArgument> fromArray(String[] args) {

        ArrayList<AppArgument> res = new ArrayList<AppArgument>();

        for (String arg : args) {

            String key = "";
            String value = "";

            if (arg.contains("=")) {

                key = arg.substring(0, arg.indexOf('='));
                value = arg.substring(arg.indexOf('=') + 1);

            } else {

                key = arg;

            }

            res.add(new AppArgument(key, value));

        }

        return res;

    }

    private String key;
    private String value;

    public AppArgument(String k, String v) {

        key = k;
        value = v;

    }

    public String getKey() {

        return key;

    }

    public String getValue() {

        return value;

    }

}
