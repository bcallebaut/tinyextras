package com.bcallebaut.tinyextras;

import de.halfbit.tinybus.TinyBus;

/**
 * Created by Benoit Callebaut on 12/12/2016.
 * Interface to be used by Application exposing a TinyBus Object globally.
 */
public interface BusApplication {
    TinyBus getBus();
}
