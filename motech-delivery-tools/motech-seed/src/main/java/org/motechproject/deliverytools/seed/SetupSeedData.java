package org.motechproject.deliverytools.seed;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetupSeedData {
    public static final String APPLICATION_CONTEXT_XML = "seed-beans.xml";

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML);
        AllSeedMethods allSeedMethods = (AllSeedMethods) context.getBean("allSeedMethods");
        allSeedMethods.run();
    }
}
