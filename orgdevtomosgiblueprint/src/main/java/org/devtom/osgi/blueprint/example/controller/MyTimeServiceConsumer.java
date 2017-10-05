package org.devtom.osgi.blueprint.example.controller;

import org.devtom.osgi.timeservice.ITimeService;

public class MyTimeServiceConsumer implements IMyTimeServiceConsumer {

    private ITimeService timeService;

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
}
