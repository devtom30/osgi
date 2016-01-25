package org.devtom.osgi.timeservice.host;

/**
 * Created by tom on 22/01/16.
 */

import org.devtom.osgi.timeservice.ITimeService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Properties;

public class Activator implements BundleActivator {

    private ServiceRegistration timeServiceRegistration;

    public void start(BundleContext context) throws Exception {
        // Create remote service properties
        Dictionary<String, Object> props = createRemoteServiceProperties();

        timeServiceRegistration = context.registerService(ITimeService.class, new TimeServiceImpl(), props);

        // Print out the service registration
        System.out.println("TimeService host registered with registration="
                + timeServiceRegistration);
    }

    public void stop(BundleContext context) throws Exception {
        if (timeServiceRegistration != null) {
            timeServiceRegistration.unregister();
            timeServiceRegistration = null;
        }
    }

    private static final String SERVICE_EXPORTED_CONFIGS = "service.exported.configs";
    private static final String DEFAULT_CONFIG = "ecf.generic.server";

    private Dictionary<String, Object> createRemoteServiceProperties() {
        Hashtable<String, Object> result = new Hashtable<String, Object>();
        // This property is required by the Remote Services specification
        // (chapter 100 in enterprise specification), and when set results
        // in RSA impl exporting as a remote service
        result.put("service.exported.interfaces", "*");
        // async interfaces is an ECF Remote Services service property
        // that allows any declared asynchronous interfaces
        // to be used by consumers.
        // See https://wiki.eclipse.org/ECF/Asynchronous_Remote_Services
        result.put("ecf.exported.async.interfaces", "*");
        // get system properties
        Properties props = System.getProperties();
        // Get OSGi service.exported.configs property
        String config = props.getProperty(SERVICE_EXPORTED_CONFIGS);
        if (config == null) {
            config = DEFAULT_CONFIG;
            result.put(DEFAULT_CONFIG + ".port", "3288");
            result.put(DEFAULT_CONFIG + ".hostname", "localhost");
        }

        result.put(SERVICE_EXPORTED_CONFIGS, config);
        // add any config properties. config properties start with
        // the config name '.' property
        for (Object k : props.keySet()) {
            if (k instanceof String) {
                String key = (String) k;
                if (key.startsWith(config))
                    result.put(key, props.get(key));
            }
        }
        return result;
    }

}

