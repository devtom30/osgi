package org.devtom.osgi.blueprint.example.controller;

import org.devtom.osgi.timeservice.ITimeService;

public class MyTimeServiceConsumer implements IMyTimeServiceConsumer {

    private ITimeService timeService;
    private TimeServiceThread thread;

    public MyTimeServiceConsumer(ITimeService tService) {
        timeService = tService;
    }

    public MyTimeServiceConsumer() {

    }

    public void getTimeFromTimeService() {
        String currTime = timeService.getCurrentTime().toString();
        System.out.println("============================");
        System.out.println("Time got from TimeService: " + currTime);
        System.out.println("============================");
    }

    public ITimeService getTimeService() {
        return timeService;
    }

    public void setTimeService(ITimeService timeService) {
        this.timeService = timeService;
    }

    public void startUp() {
        System.out.println("MyTimeServiceConsumer startUp()");
        thread = new TimeServiceThread(timeService);
        thread.run();
    }

    public void stopThread() {
        thread.stopThread();
    }

    public static class TimeServiceThread extends Thread {

        private volatile boolean active = true;
        private final ITimeService timeService;

        public TimeServiceThread(ITimeService tService) {
            this.timeService = tService;
        }

        public void run() {
            while (active) {
                try {
                    Thread.sleep(5000);
                    if (active) {
                        System.out.println("TimeServiceThread: uh!");
                        System.out.println("TimeServiceThread: " + timeService.getCurrentTime());
                    }
                } catch (Exception e) {
                    System.out.println("Thread interrupted " + e.getMessage());
                }
            }
        }

        public void stopThread() {
            active = false;
        }
    }
}
