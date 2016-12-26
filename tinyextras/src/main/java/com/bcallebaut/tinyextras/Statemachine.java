package com.bcallebaut.tinyextras;

/**
 * Created by Benoit Callebaut on 12/12/2016.
 * Represents a State Machine
 */
public interface Statemachine<E> extends Bus<E> {
    void initFSM();

    void transitionTo(int state);

    void fireEvent(Object event);

    int getCurrentState();

    void setTraceTag(String tag);
}
