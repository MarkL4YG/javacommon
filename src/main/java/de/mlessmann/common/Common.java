package de.mlessmann.common;

import java.util.regex.Pattern;

/**
 * Created by Life4YourGames on 29.06.16.
 */
public class Common {

    public static int negateInt(int i) { return i * (-1); }

    public static String stripVersion(String s) {
        return s.replaceAll("(?!\\.)([a-zA-Z. ]*)", "");
    }

    /**
     * Returns:
     * -1 a is higher
     * 0 even
     * 1 b is higher
     */
    public static int compareVersions(String a, String b) {

        a = stripVersion(a);
        b = stripVersion(b);

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

    public static boolean areCompatible(String a, String b) {
        a = stripVersion(a);
        b = stripVersion(b);

        String[] first = a.split(Pattern.quote("."));
        String[] second = b.split(Pattern.quote("."));
        int max = (int) Math.ceil(((first.length - 1) + (second.length - 1)) / 2);
        for (int i = max; i >= 0; i--) {
            int c = 0, d = 0;
            if (first.length >= i) {
                c = Integer.parseInt(first[i]);
            }

            if (second.length >= i) {
                d = Integer.parseInt(second[i]);
            }
            //First higher than second
            if (c > d) {
                if (max - i > 0)
                    return false;
            }
            //Second higher than first
            if (d > c) {
                if (max - i > 0)
                    return false;
            }
        }
        return true;
    }

}
