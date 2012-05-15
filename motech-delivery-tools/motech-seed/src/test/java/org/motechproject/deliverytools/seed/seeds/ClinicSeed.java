package org.motechproject.deliverytools.seed.seeds;

import org.motechproject.deliverytools.seed.Seed;
import org.motechproject.deliverytools.seed.domain.Clinic;
import org.motechproject.deliverytools.seed.repository.AllClinics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClinicSeed{

    @Autowired
    private AllClinics allClinics;

    @Seed(priority = 1, version = "1.1")
    public void load() throws InterruptedException {
        Clinic clinic = new Clinic("C1", "city");
        allClinics.add(clinic);
    }
}
