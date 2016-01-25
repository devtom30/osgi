package org.devtom.osgi.timeservice.consumer;

/**
 * Created by tom on 25/01/16.
 */
public class TimeConsumerThread extends Thread {
    private volatile boolean active = true;

    public void run() {
        while (active) {
            System.out.println("Hello OSGi console");
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                System.out.println("Thread interrupted " + e.getMessage());
            }
        }
    }

    public void stopThread() {
        System.out.println("Goodbye OSGi console");
        active = false;
    }
}
