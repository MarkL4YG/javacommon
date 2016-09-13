package de.mlessmann.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Life4YourGames on 13.09.16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.PACKAGE})
public @interface Plugin {
    String pluginID();
    String author();

    /**
     * Version of plugin
     */
    String version();

    /**
     * Version the plugin was built against
     */
    String targetVersion();

    String description() default "";
    String url() default "";
}
