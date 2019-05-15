package com.nurturecloud.geospatial;

public class SuburbGeospatialPoint implements GeospatialPoint {
    private final double latitude;
    private final double longitude;

    /**
     * Builds immutable geospatial point with the given latitude and longitude.
     *
     * @param latitude
     * @param longitude
     */
    public SuburbGeospatialPoint(final double latitude, final double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public Double getLatitude() {
        return this.latitude;
    }

    @Override
    public Double getLongitude() {
        return this.longitude;
    }
}
