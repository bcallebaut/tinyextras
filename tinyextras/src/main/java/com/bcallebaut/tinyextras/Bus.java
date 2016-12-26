package com.bcallebaut.tinyextras;

import de.halfbit.tinybus.Subscribe;

/**
 * Created by Benoit Callebaut on 12/12/2016.
 * Represents a class being a Bus subscriber
 */
public interface Bus<E> {
    void post(Object event);

    @Subscribe(mode = Subscribe.Mode.Background)
    void processEvent(E event);
}
