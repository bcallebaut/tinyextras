package com.bcallebaut.tinyextras;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import de.halfbit.tinybus.TinyBus;

/**
 * Created by Benoit Callebaut on 1/12/2016.
 * Activity interacting with the Event Bus
 */
public abstract class BusActivity<E> extends Activity implements Bus<E> {

    private TinyBus bus = null;

    protected TinyBus getBus() {
        return bus;
    }

    @Override
    public void post(Object event) {

//        System.out.println("Posting event " + event.toString()+" BUS hashcode : " + bus.hashCode() + " " + bus.toString());
        bus.post(event);
    }

    /**
     * Called when the activity is starting.  This is where most initialization
     * should go: calling {@link #setContentView(int)} to inflate the
     * activity's UI, using {@link #findViewById} to programmatically interact
     * with widgets in the UI, calling
     * {@link #managedQuery(Uri, String[], String, String[], String)} to retrieve
     * cursors for data being displayed, etc.
     * <p/>
     * <p>You can call {@link #finish} from within this function, in
     * which case onDestroy() will be immediately called without any of the rest
     * of the activity lifecycle ({@link #onStart}, {@link #onResume},
     * {@link #onPause}, etc) executing.
     * <p/>
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     * @see #onStart
     * @see #onSaveInstanceState
     * @see #onRestoreInstanceState
     * @see #onPostCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBus();
    }

    /**
     * Called by the system to notify a Service that it is no longer used and is being removed.  The
     * service should clean up any resources it holds (threads, registered
     * receivers, etc) at this point.  Upon return, there will be no more calls
     * in to this Service object and it is effectively dead.  Do not call this method directly.
     */
    @Override
    public void onDestroy() {
        if (bus != null)
            bus.unregister(this);
        super.onDestroy();
    }

    private void initBus() {
        if (bus == null) {
            bus = ((BusApplication) getApplicationContext()).getBus();//TinyBus.from(getApplicationContext());
            System.out.println("BUS hashcode : " + bus.hashCode() + " " + bus.toString());
            bus.register(this);
            internalInit();
        }
    }

    protected void internalInit() {
    }

}
