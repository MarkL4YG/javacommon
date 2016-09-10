package de.mlessmann.common.annotations;

/**
 * Created by Life4YourGames on 08.08.16.
 *
 * Indicator of abstraction.
 * The higher the less abstract the method,field,class,...
 * This is supposed to indicate what methods a client developer should use
 * E.g. which of the multiple methods about provider discovery.
 * For convenience you should use 1
 *
 * Yes, these are purely decorative
 */
public @interface API {

    int APILevel() default 1;

    public static int APIVERSION = 1;
    public static String PROTOVERSION = "1.1.0.0";

}
