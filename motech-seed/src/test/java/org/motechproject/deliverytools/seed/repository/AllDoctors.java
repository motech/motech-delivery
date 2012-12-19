package org.motechproject.deliverytools.seed.repository;

import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;
import org.motechproject.deliverytools.seed.domain.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class AllDoctors extends CouchDbRepositorySupport<Doctor> {

    @Autowired
    protected AllDoctors(@Qualifier("couchDbConnector") CouchDbConnector db) {
        super(Doctor.class, db);
    }

}
