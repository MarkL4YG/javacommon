package de.mlessmann.logging;

import java.util.logging.Level;

/**
 * Created by Life4YourGames on 13.09.16.
 */
public interface ILogReceiver {

    void finest(Object... message);
    void finer(Object... message);
    void fine(Object... message);
    void info(Object... message);
    void warning(Object... message);
    void severe(Object... message);

    void log(Level level, Object... message);

    ILogReceiver getChild(String childPrefix);

    public class Dummy implements ILogReceiver {

        public static ILogReceiver newDummy() {
            return new ILogReceiver.Dummy();
        }

        @Override
        public void log(Level level, Object... message) {
            //DoNothing
        }

        @Override
        public void finest(Object... message) {
        }

        @Override
        public void finer(Object... message) {
        }

        @Override
        public void fine(Object... message) {
        }

        @Override
        public void info(Object... message) {
        }

        @Override
        public void warning(Object... message) {
        }

        @Override
        public void severe(Object... message) {
        }

        @Override
        public ILogReceiver getChild(String childPrefix) {
            return new MarkL4YGLogger.ChildReceiver(childPrefix, this);
        }
    }
}
