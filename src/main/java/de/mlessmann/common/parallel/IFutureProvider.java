package de.mlessmann.common.parallel;

/**
 * Created by Life4YourGames on 04.10.16.
 */
public interface IFutureProvider<T> {
    T getPayload(IFuture future);
}
