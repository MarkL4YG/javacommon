package de.mlessmann.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Life4YourGames on 27.09.16.
 *
 * Mainly to mark classes that implement a certain index type
 * Used e.g. in my updater project which aims to support more than just
 * GitHub-Releases by providing an API using this annotation
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface IndexType {
}
