package org.motechproject.deliverytools.seed.seeds;

import org.motechproject.deliverytools.seed.Seed;
import org.motechproject.deliverytools.seed.domain.Regimen;
import org.motechproject.deliverytools.seed.repository.AllDrugs;
import org.motechproject.deliverytools.seed.repository.AllRegimens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegimenSeed{

    @Autowired
    private AllRegimens allRegimens;

    @Autowired
    private AllDrugs allDrugs;

    @Seed(priority = 0)
    public void load() throws InterruptedException {
        Regimen regimen = new Regimen("regimen", allDrugs.getAll());
        allRegimens.add(regimen);
    }
}
