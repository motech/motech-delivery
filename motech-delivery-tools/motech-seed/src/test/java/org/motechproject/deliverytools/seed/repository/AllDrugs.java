package org.motechproject.deliverytools.seed.repository;

import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;
import org.motechproject.deliverytools.seed.domain.Drug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class AllDrugs  extends CouchDbRepositorySupport<Drug> {

    @Autowired
    protected AllDrugs(@Qualifier("couchDbConnector") CouchDbConnector db) {
        super(Drug.class, db);
    }

}
