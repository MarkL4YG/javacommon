package de.mlessmann.logging;

import java.util.logging.Level;

/**
 * Created by Life4YourGames on 13.09.16.
 */
public interface ILogReceiver {

    void finest(Object sender, Object... message);
    void finer(Object sender, Object... message);
    void fine(Object sender, Object... message);
    void info(Object sender, Object... message);
    void warning(Object sender, Object... message);
    void severe(Object sender, Object... message);

    void log(Object sender, Level level, Object... message);

    public class Dummy implements ILogReceiver {

        public static ILogReceiver newDummy() {
            return new ILogReceiver.Dummy();
        }

        @Override
        public void log(Object sender, Level level, Object... message) {
            //DoNothing
        }

        @Override
        public void finest(Object sender, Object... message) {
        }

        @Override
        public void finer(Object sender, Object... message) {
        }

        @Override
        public void fine(Object sender, Object... message) {
        }

        @Override
        public void info(Object sender, Object... message) {
        }

        @Override
        public void warning(Object sender, Object... message) {
        }

        @Override
        public void severe(Object sender, Object... message) {
        }
    }
}
