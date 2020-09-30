
package com.ibm.lcd.cfm.monitor.util;

import java.lang.reflect.InvocationTargetException;
import javax.swing.JPanel;

public abstract class ThreadPanel extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 6904158087798852692L;

	//final String CLASS_NAME = "ThreadPanel";

    private volatile Thread workerThread = null; // Worker thread object.
    private long workerInterval = 1000; // Interval of waking up thread.
    private boolean workerRefresh = true;
    //private boolean workerInitial = true;
    private long lastSleepTimeMillis = 0;

    // ------------------------------------------------------- Public Methods

    public final void setInterval(long interval) {
        workerInterval = interval;
    }

    // ------------------------------------------------------- Thread Methods

/*
    public final static void doYield() throws InterruptedException {
        if (Thread.interrupted())
            throw new InterruptedException();
        else
            Thread.yield();
    }
*/
    /**
     *
     */
    public final boolean isRunning() {
        Thread uncertain = workerThread;
        return (uncertain != null && uncertain.isAlive());
    }

    /**
     *
     */
    public final void start() {
        restart(true);
    }

    /**
     *
     */
    public final void restart(boolean workerRefresh)
    {
        Thread uncertain = workerThread;
        this.workerRefresh = workerRefresh;
        if (uncertain != null && uncertain.isAlive())
            stop();

        if (workerThread == null) {
            workerThread = new Thread(this);
            workerThread.start();
        }
    }

    /**
     *
     */
    public final void stop() {
        if (workerThread != null) {
            Thread moribund = workerThread;
            workerThread = null;
            if (moribund.isAlive()) {
                try {
                    moribund.interrupt();
                    moribund.join();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     *
     */
    public final void run() {
    	
        Thread thisThread = Thread.currentThread();
        if (workerThread != thisThread)
            return;
        if (!beforeRun())
            return;
        long sleepTimeMillis = workerInterval;
        try {
            while (workerThread == thisThread) {
                if (!doRun(thisThread, workerRefresh))
                    break;
                sleepTimeMillis = workerInterval;
                if (!workerRefresh) {
                    if (lastSleepTimeMillis < workerInterval)
                        sleepTimeMillis = workerInterval - lastSleepTimeMillis;
                    workerRefresh = true;
                }
                lastSleepTimeMillis = System.currentTimeMillis();
                Thread.sleep(sleepTimeMillis);
            }
        } catch (InterruptedException ex) {
        	ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }
        lastSleepTimeMillis = System.currentTimeMillis() - lastSleepTimeMillis;
        afterRun();
    }

    // ----------------------------------------------------- Abstract Methods

    /**
     * This method will be invoked before the thread is started.
     *
     * @return true if the thread can start, false otherwise.
     */
    protected boolean beforeRun() {
        return true;
    }

    /**
     * This method will be invoked when the thread waked up.
     *
     * @param thisThread The current thread.
     * @param workerRefresh whether thread needs to refresh (not wake up).
     * @return true if the thread can continue, false otherwise.
     */
    protected abstract boolean doRun(Thread thisThread, boolean workerRefresh) throws InterruptedException, InvocationTargetException;

    /**
     * This method will be invoked after the thread is stopped.
     *
     */
    protected void afterRun() {
    }

}
