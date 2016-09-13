package de.mlessmann.logging;

import java.util.logging.Level;

/**
 * Created by Life4YourGames on 13.09.16.
 */
public interface ILogReceiver {

    void onMessage(Object sender, Level level, String message);

    void onException(Object sender, Level level, Exception exception);

}
