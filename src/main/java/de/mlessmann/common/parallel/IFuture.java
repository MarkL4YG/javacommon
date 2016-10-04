package de.mlessmann.common.parallel;

import java.util.NoSuchElementException;

/**
 * Created by Life4YourGames on 04.10.16.
 */
public interface IFuture<T> {
    void registerListener(IFutureListener listener);
    void unregisterListener(IFutureListener listener);

    boolean isPresent();
    T get() throws NoSuchElementException;
    T getOrElse(T def);
}
