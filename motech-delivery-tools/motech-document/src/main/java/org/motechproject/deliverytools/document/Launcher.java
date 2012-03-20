package org.motechproject.deliverytools.document;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {
    public static final String APPLICATION_CONTEXT_XML = "document-beans.xml";

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML);
    }
}
