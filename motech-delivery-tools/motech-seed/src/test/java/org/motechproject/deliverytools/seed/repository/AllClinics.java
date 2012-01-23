package org.motechproject.deliverytools.seed.repository;

import org.ektorp.CouchDbConnector;
import org.motechproject.dao.MotechBaseRepository;
import org.motechproject.deliverytools.seed.domain.Clinic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class AllClinics extends MotechBaseRepository<Clinic> {

    @Autowired
    protected AllClinics(@Qualifier("couchDbConnector") CouchDbConnector db) {
        super(Clinic.class, db);
    }
}