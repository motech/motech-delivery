package org.motechproject.deliverytools.seed;

import org.junit.Test;
import org.motechproject.deliverytools.util.Version;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SeedMethodTest {
    @Test
    public void shouldCompareFirstBasedOnVersionIfVersionsAreNotEqual() {
        SeedMethod thisMethod = new SeedMethod(null, null, 0, "1.0", false);
        SeedMethod otherMethod = new SeedMethod(null, null, 0, "2.0", false);

        assertEquals(-1, thisMethod.compareTo(otherMethod));
    }

    @Test
    public void shouldCompareBasedOnPriorityIfVersionsAreEqual() {
        SeedMethod thisMethod = new SeedMethod(null, null, 0, "2.0", false);
        SeedMethod otherMethod = new SeedMethod(null, null, 1, "2.0", false);

        assertEquals(1, thisMethod.compareTo(otherMethod));
    }

    @Test
    public void shouldRunIfTheCurrentMinorVersionNumberIsLessThanTheVersionNumberOnTheMethod() {
        SeedMethod method = new SeedMethod(null, null, 0, "1.1", false);

        assertTrue(method.shouldRunFor(new Version("1.0")));
        assertFalse(method.shouldRunFor(new Version("1.2")));
    }

    @Test
    public void shouldNotRunIfTheMajorVersionsDoNotMatch() {
        SeedMethod method = new SeedMethod(null, null, 0, "1.1", false);

        assertFalse(method.shouldRunFor(new Version("2.2")));
    }
}
