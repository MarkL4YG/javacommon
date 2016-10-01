package de.mlessmann.logging;

import java.util.logging.Level;

/**
 * Created by Life4YourGames on 13.09.16.
 */
public interface ILogReceiver {

    void onMessage(Object sender, Level level, String message);

    void onException(Object sender, Level level, Exception exception);

    public class Dummy implements ILogReceiver {
        public static ILogReceiver newDummy() {
            return new ILogReceiver.Dummy();
        }

        @Override
        public void onMessage(Object sender, Level level, String message) {
            //DoNothing
        }

        @Override
        public void onException(Object sender, Level level, Exception exception) {
            //DoNothing
        }
    }
}
