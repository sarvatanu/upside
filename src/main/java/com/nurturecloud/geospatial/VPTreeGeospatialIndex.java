package com.nurturecloud.geospatial;

import com.eatthepath.jvptree.VPTree;

import java.util.Collection;

public class VPTreeGeospatialIndex<E extends GeospatialPoint> extends VPTree<GeospatialPoint, E> {

    private static final HaversineDistanceFunction HAVERSINE_DISTANCE_FUNCTION = new HaversineDistanceFunction();

    public VPTreeGeospatialIndex(final Collection<E> points) {
        super(HAVERSINE_DISTANCE_FUNCTION, points);
    }
}
