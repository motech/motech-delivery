package org.motechproject.deliverytools.util;

public class Version implements Comparable<Version> {
    private Integer majorVersionNumber;
    private Integer minorVersionNumber;

    public Version(String version) {
        String[] versionArray = version.split("\\.");
        this.majorVersionNumber = Integer.parseInt(versionArray[0]);
        this.minorVersionNumber = Integer.parseInt(versionArray[1]);
    }

    public boolean isGreaterThanOrEqualTo(Version otherVersion) {
        return majorVersionNumber.equals(otherVersion.majorVersionNumber) && minorVersionNumber >= otherVersion.minorVersionNumber;
    }

    @Override
    public int compareTo(Version version) {
        return majorVersionNumber.equals(version.majorVersionNumber) ?
                minorVersionNumber.compareTo(version.minorVersionNumber) :
                majorVersionNumber.compareTo(version.majorVersionNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Version version = (Version) o;

        if (majorVersionNumber != null ? !majorVersionNumber.equals(version.majorVersionNumber) : version.majorVersionNumber != null)
            return false;
        if (minorVersionNumber != null ? !minorVersionNumber.equals(version.minorVersionNumber) : version.minorVersionNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = majorVersionNumber != null ? majorVersionNumber.hashCode() : 0;
        result = 31 * result + (minorVersionNumber != null ? minorVersionNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return majorVersionNumber + "." + minorVersionNumber;
    }
}
