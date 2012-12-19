package org.motechproject.deliverytools.util;

import org.junit.Test;

import static junit.framework.Assert.*;

public class VersionTest {
    @Test
    public void shouldReturnTrueIfTheCurrentVersionIsGreaterThanTheOtherVersion() throws Exception {
        Version version = new Version("1.1");
        Version otherVersion = new Version("1.0");

        assertTrue(version.isGreaterThanOrEqualTo(otherVersion));
    }

    @Test
    public void shouldReturnFalseIfTheCurrentVersionIsNotGreaterThanTheOtherVersion() throws Exception {
        Version version = new Version("1.0");
        Version otherVersion = new Version("1.1");

        assertFalse(version.isGreaterThanOrEqualTo(otherVersion));
    }

    @Test
    public void shouldReturnTrueIfTheCurrentVersionIsEqualToTheOtherVersion() throws Exception {
        Version version = new Version("1.0");
        Version otherVersion = new Version("1.0");

        assertTrue(version.isGreaterThanOrEqualTo(otherVersion));
    }

    @Test
    public void shouldReturnFalseIfTheMajorVersionNumbersDoNotMatch() throws Exception {
        Version version = new Version("3.2");
        Version otherVersion = new Version("2.1");

        assertFalse(version.isGreaterThanOrEqualTo(otherVersion));
    }

    @Test
    public void shouldCompareBasedOnMinorVersionNumberIfMajorVersionNumbersAreEqual() {
        Version version = new Version("3.1");
        Version otherVersion = new Version("3.2");

        assertEquals(-1, version.compareTo(otherVersion));

        version = new Version("3.0");
        otherVersion = new Version("3.0");

        assertEquals(0, version.compareTo(otherVersion));

        version = new Version("3.2");
        otherVersion = new Version("3.1");

        assertEquals(1, version.compareTo(otherVersion));
    }

    @Test
    public void shouldCompareBasedOnMajorVersionNumberIfMajorVersionNumbersAreNotEqual() {
        Version version = new Version("2.1");
        Version otherVersion = new Version("3.1");

        assertEquals(-1, version.compareTo(otherVersion));

        version = new Version("3.1");
        otherVersion = new Version("3.1");

        assertEquals(0, version.compareTo(otherVersion));

        version = new Version("3.1");
        otherVersion = new Version("2.1");

        assertEquals(1, version.compareTo(otherVersion));
    }
}
