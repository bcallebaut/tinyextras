package com.bcallebaut.tinyextras;

/**
 * Created by Benoit Callebaut on 12/12/2016.
 * Represents a Finite State Machine Handler.
 */
public class FSMHandler<E> {
    private Statemachine<E> fsm;

    public FSMHandler() {
    }

    public Statemachine<E> getFsm() {
        return fsm;
    }

    public void setFsm(Statemachine<E> fsm) {
        this.fsm = fsm;
    }

    protected final void sendEvent(Object event) {
        fsm.post(event);
    }

    protected final void transitionTo(int state) {
        fsm.transitionTo(state);
    }

    public void initFSM() {
    }
}
