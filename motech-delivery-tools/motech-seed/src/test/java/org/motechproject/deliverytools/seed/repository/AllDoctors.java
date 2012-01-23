package org.motechproject.deliverytools.seed.repository;

import org.ektorp.CouchDbConnector;
import org.motechproject.dao.MotechBaseRepository;
import org.motechproject.deliverytools.seed.domain.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class AllDoctors extends MotechBaseRepository<Doctor> {

    @Autowired
    protected AllDoctors(@Qualifier("couchDbConnector") CouchDbConnector db) {
        super(Doctor.class, db);
    }
}
