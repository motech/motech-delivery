package org.motechproject.deliverytools.seed.seeds;

import org.motechproject.deliverytools.seed.Seed;
import org.motechproject.deliverytools.seed.domain.Drug;
import org.motechproject.deliverytools.seed.repository.AllDrugs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DrugSeed{

    @Autowired
    private AllDrugs allDrugs;

    @Seed(priority = 1, version = "1.1")
    public void load() throws InterruptedException {
        Drug drug1 = new Drug("D1");
        Drug drug2 = new Drug("D2");
        Drug drug3 = new Drug("D3");
        Drug drug4 = new Drug("D4");

        allDrugs.add(drug1);
        allDrugs.add(drug2);
        allDrugs.add(drug3);
        allDrugs.add(drug4);
    }
}
