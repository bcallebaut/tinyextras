package com.bcallebaut.tinyextras;

import de.halfbit.tinymachine.TinyMachine;

/**
 * Created by Benoit Callebaut on 1/12/2016.
 * Service implementing a Statemachine
 */
public abstract class StatemachineService<E> extends BusService<E> implements Statemachine<E> {

    private TinyMachine fsm;
    private FSMHandler<E> handler;

    protected FSMHandler<E> createFSMHandler() {
        return new FSMHandler<>();
    }

    protected abstract int getInitialState();

    @Override
    protected final void internalInit() {
        handler = createFSMHandler();
        handler.setFsm(this);
        fsm = new TinyMachine(handler, getInitialState());
    }

    public final void initFSM() {
        handler.initFSM();
    }

    /**
     * Moves state machine in a new given state. If state machine is already in that state,
     * then this method has no effect. Otherwise, if exists, <code>Type.OnExit</code> event
     * handler for the current state is called first and then <code>Type.OnEntry</code> event
     * handler for new state is called.
     *
     * @param state new state to put state machine into
     */
    public final void transitionTo(int state) {
        fsm.transitionTo(state);
    }

    /**
     * Forwards an event into state machine. State machine will deliver the event to a
     * handler methods responsible for its processing. If there is no handler method found,
     * then event gets silently ignored and this call has no effect.
     *
     * @param event event to be delivered to a handler method
     */
    public final void fireEvent(Object event) {
        fsm.fireEvent(event);
    }

    /**
     * Returns current machine state.
     *
     * @return current machine state
     */
    public final int getCurrentState() {
        return fsm.getCurrentState();
    }

    /**
     * Enables traces and sets tag to be used for <code>Log.d()</code> output.
     * <code>TinyMachine</code> will trace all processed events and state transitions including
     * events for which handlers in current state are missed.
     *
     * @param tag the name of tag to be used in <code>Log.d()</code>
     */
    public final void setTraceTag(String tag) {
        fsm.setTraceTag(tag);
    }

}
