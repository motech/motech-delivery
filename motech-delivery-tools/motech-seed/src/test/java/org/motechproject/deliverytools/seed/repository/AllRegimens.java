package org.motechproject.deliverytools.seed.repository;


import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;
import org.motechproject.deliverytools.seed.domain.Regimen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class AllRegimens  extends CouchDbRepositorySupport<Regimen> {

    @Autowired
    protected AllRegimens(@Qualifier("couchDbConnector") CouchDbConnector db) {
        super(Regimen.class, db);
    }

}
