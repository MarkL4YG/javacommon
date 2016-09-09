package de.mlessmann.common;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by Life4YourGames on 13.05.16.
 */
public class L4YGRandom {

    public static final String ALPHANUMERIC = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static SecureRandom secureRandom;

    public static Random random;


    public static void initializeSecRnd() {

        secureRandom = new SecureRandom();

    }

    public static void initializeRnd() {

        random = new Random();

    }

    public static void initRndIfNotAlready() {

        if (random == null) {
            initializeRnd();
        }

    }

    public static void initSecRndIfNotAlready() {

        if (secureRandom == null) {
            initializeSecRnd();
        }

    }

    public static String genRandomString(String charSource, int len) {

        char[] chars = new char[len];

        for (int I = 0; I<len; I++) {

            chars[I] = charSource.charAt(random.nextInt(charSource.length() - 1));

        }

        return new String(chars);

    }

    public static String genRandomAlphaNumString(int len) {

        return genRandomString(L4YGRandom.ALPHANUMERIC, len);

    }

}
