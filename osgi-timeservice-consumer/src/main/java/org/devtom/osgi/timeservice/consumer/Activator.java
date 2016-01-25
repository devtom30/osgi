package org.devtom.osgi.timeservice.consumer;

import org.devtom.osgi.timeservice.ITimeService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * Created by tom on 25/01/16.
 */
public class Activator implements BundleActivator {

    private BundleContext context;
    private ITimeService service;
    private TimeConsumerThread thread;

    public void start(BundleContext context) throws Exception {
        this.context = context;
        // Register directly with the service
        ServiceReference reference = context
                .getServiceReference(ITimeService.class.getName());
        service = (ITimeService) context.getService(reference);
        System.out.println("Consumer has started at " + service.getCurrentTime());

        thread = new TimeConsumerThread();
        thread.start();
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("Consumer has stopped at " + service.getCurrentTime());

        thread.stopThread();
        thread.join();
    }
}
