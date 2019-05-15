package com.nurturecloud.geospatial;

import org.junit.Test;

public class HaversineDistanceFunctionTest {

    @Test
    public void testGetDistanceShouldReturnZeroWhenStartAndEndPointIsSame() {
        final HaversineDistanceFunction distanceFunction =
                new HaversineDistanceFunction();

        final SuburbGeospatialPoint start = new SuburbGeospatialPoint(151.209900, -33.865143);
        final SuburbGeospatialPoint end = new SuburbGeospatialPoint(151.209900, -33.865143);

        assert distanceFunction.getDistance(start, end) == 0;

    }

    @Test
    public void testGetDistanceShouldReturnSameDistance() {
        final HaversineDistanceFunction distanceFunction =
                new HaversineDistanceFunction();

        final SuburbGeospatialPoint syd = new SuburbGeospatialPoint(151.209900, -33.865143);
        final SuburbGeospatialPoint mel = new SuburbGeospatialPoint(144.96332, -37.814);

        assert distanceFunction.getDistance(syd, mel) == distanceFunction.getDistance(mel, syd);

    }
}
