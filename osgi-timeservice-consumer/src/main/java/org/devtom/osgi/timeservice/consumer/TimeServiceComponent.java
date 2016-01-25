package org.devtom.osgi.timeservice.consumer;

import org.devtom.osgi.timeservice.ITimeService;

/**
 * Created by tom on 22/01/16.
 */
public class TimeServiceComponent {
    // Called by DS upon ITimeService discovery
    void bindTimeService(ITimeService timeService) {
        System.out.println("Discovered ITimeService via DS.  Instance="+timeService);
        // Call the service and print out result!
        System.out.println("Current time on remote is: " + timeService.getCurrentTime());
    }

    // Called by DS upon ITimeService undiscovery
    void unbindTimeService(ITimeService timeService) {
        System.out.println("Undiscovered ITimeService via DS.  Instance="+timeService);
    }
}
