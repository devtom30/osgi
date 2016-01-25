package org.devtom.osgi.timeservice.host;

import org.devtom.osgi.timeservice.ITimeService;

/**
 * Created by tom on 22/01/16.
 */
public class TimeServiceImpl implements ITimeService {

    public Long getCurrentTime() {
        System.out.println("TimeServiceImpl.  Received call to getCurrentTime()");
        return new Long(System.currentTimeMillis());
    }
}
