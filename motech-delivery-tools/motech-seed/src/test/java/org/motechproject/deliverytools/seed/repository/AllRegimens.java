package org.motechproject.deliverytools.seed.repository;


import org.ektorp.CouchDbConnector;
import org.motechproject.dao.MotechAuditableRepository;
import org.motechproject.deliverytools.seed.domain.Regimen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class AllRegimens  extends MotechAuditableRepository<Regimen> {

    @Autowired
    protected AllRegimens(@Qualifier("couchDbConnector") CouchDbConnector db) {
        super(Regimen.class, db);
    }
}
