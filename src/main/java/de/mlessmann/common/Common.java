package de.mlessmann.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Life4YourGames on 29.06.16.
 */
public class Common {

    public static int negateInt(int i) { return i * (-1); }

    public static String getFirstVersion(String s) {

        String pString1 = "[v][0-9]";

        Pattern p1 = Pattern.compile(pString1);

        Matcher m1 = p1.matcher(s);

        int start = 1;

        if (m1.find()) start = m1.start();

        String pString2 = "(0-9)([.+?]/s)";

        int end = s.indexOf(" ", start  + 1);

        if (end == -1) end = s.length();

        return s.substring(start == -1 ? 0 : start + 1, end);

    }

    /**
     * Returns:
     * -1 a is higher
     * 0 even
     * 1 b is higher
     */
    public static int compareVersions(String a, String b) {

        String[] first = a.split(Pattern.quote("."));
        String[] second = b.split(Pattern.quote("."));

        int max = (int) Math.ceil(((first.length - 1) + (second.length - 1)) / 2);

        for (int i = 0; i <= max; i++) {

            int c = 0, d = 0;

            if (first.length >= i) {

                c = Integer.parseInt(first[i]);

            }

            if (second.length >= i) {

                d = Integer.parseInt(second[i]);

            }

            //First higher than second
            if (c > d) {

                return - 1;

            }

            //Second higher than first
            if (d > c) {

                return 1;

            }

        }

        return 0;

    }

}
