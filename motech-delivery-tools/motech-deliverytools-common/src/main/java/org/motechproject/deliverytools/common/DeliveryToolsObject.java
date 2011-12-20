package org.motechproject.deliverytools.common;

import org.apache.log4j.Logger;

public class DeliveryToolsObject {
    protected Logger logger = Logger.getLogger(this.getClass());

    static {
        doNotInheritRootLoggerFor("org.motechproject");
        doNotInheritRootLoggerFor("org.motechproject.deliverytools");
    }

    protected static void doNotInheritRootLoggerFor(String name) {
        Logger logger = Logger.getLogger(name);
        logger.setAdditivity(false);
    }

    protected void logInfo(String message, String ... params) {
        logger.info(String.format(message, params));
    }

    protected void logInfo(String message, Object ... params) {
        logger.info(String.format(message, params));
    }
}
