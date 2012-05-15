package org.motechproject.deliverytools.seed;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.deliverytools.seed.domain.Clinic;
import org.motechproject.deliverytools.seed.domain.Regimen;
import org.motechproject.deliverytools.seed.repository.AllClinics;
import org.motechproject.deliverytools.seed.repository.AllDoctors;
import org.motechproject.deliverytools.seed.repository.AllRegimens;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static junit.framework.Assert.assertEquals;

public class SetupSeedDataIT {

    private ApplicationContext context;

    @Before
    public void setUp() {
        context = new ClassPathXmlApplicationContext(SetupSeedData.APPLICATION_CONTEXT_XML);
        deleteDB();
    }

    @Test
    public void shouldLoadAllClassAnnotatedAsSeed() throws Exception {
        SetupSeedData.main(new String[]{});

        AllClinics allClinics = (AllClinics) context.getBean("allClinics");
        Clinic dbClinic = allClinics.getAll().get(0);

        AllDoctors allDoctors = (AllDoctors) context.getBean("allDoctors");
        Clinic doctorClinic = allDoctors.getAll().get(0).clinic();
        assertEquals(dbClinic.name(), doctorClinic.name());
        assertEquals(dbClinic.city(), doctorClinic.city());


        AllRegimens allRegimens = (AllRegimens) context.getBean("allRegimens");
        Regimen regimen = allRegimens.getAll().get(0);
        assertEquals("regimen", regimen.name());
        assertEquals(5, regimen.drugs().size());
    }

    @After
    public void tearDown() {
//        deleteDB();
    }

    private void deleteDB() {
        CouchDbInstance dbInstance = (CouchDbInstance) context.getBean("couchDbInstance");
        CouchDbConnector dbConnector = (CouchDbConnector) context.getBean("couchDbConnector");
        String dbName = dbConnector.getDatabaseName();
        dbInstance.deleteDatabase(dbName);
    }
}
