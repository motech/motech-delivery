package org.motechproject.deliverytools.seed.seeds;

import org.motechproject.deliverytools.seed.Seed;
import org.motechproject.deliverytools.seed.domain.Clinic;
import org.motechproject.deliverytools.seed.domain.Doctor;
import org.motechproject.deliverytools.seed.repository.AllClinics;
import org.motechproject.deliverytools.seed.repository.AllDoctors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DoctorSeed{
    
    @Autowired
    private AllDoctors allDoctors;
    @Autowired
    private AllClinics allClinics;

    @Seed(priority = 0, version = "1.1")
    public void load() throws InterruptedException {
        List<Clinic> clinics = allClinics.getAll();
        Doctor doctor = new Doctor("dre",clinics.get(0));
        allDoctors.add(doctor);
    }
}


