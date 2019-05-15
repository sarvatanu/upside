package com.nurturecloud.service;

import com.nurturecloud.geospatial.GeospatialPoint;
import com.nurturecloud.geospatial.SuburbGeospatialPoint;
import com.nurturecloud.geospatial.VPTreeGeospatialIndex;
import com.nurturecloud.model.Suburb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SuburbFinderService implements FinderService {

    private SuburbLoaderService suburbLoaderService;

    public SuburbFinderService() {
        suburbLoaderService = new SuburbLoaderService();
    }

    @Override
    public Map<String, List<Suburb>> findNearbySuburbs(final Long postcode,
                                                       final String locality) {

        Suburb matchedSuburb = findMatchingSuburb(postcode, locality);

        if (matchedSuburb == null) {
            return null;
        }
        if (matchedSuburb.hasGeocodes()) {
            Map<String, List<Suburb>> combinedSuburbs = new HashMap<>();
            GeospatialPoint centroid =
                    new SuburbGeospatialPoint(matchedSuburb.getLatitude(), matchedSuburb.getLongitude());

            final VPTreeGeospatialIndex<Suburb> index = new VPTreeGeospatialIndex<>(suburbLoaderService.getSuburbs());

            combinedSuburbs.put("Nearby", index.getNearestNeighbors(centroid, 15));
            List<Suburb> fringesSuburbs = index.getAllWithinDistance(centroid, 50 * 1000);
            List<Suburb> selectedFringeSuburb = IntStream.range(1, 15).mapToObj(i ->
                    fringesSuburbs.get(i)).collect(Collectors.toList());
            combinedSuburbs.put("Fringe", selectedFringeSuburb);
            return combinedSuburbs;
        }
        return null;
    }

    private Suburb findMatchingSuburb(final Long postcode,
                                      final String locality) {

        Optional<Suburb> suburb = suburbLoaderService.getSuburbs().stream()
                .filter(sub ->
                        sub.getLocality().equalsIgnoreCase(locality)
                                && sub.getPcode().equals(postcode)
                ).findFirst();

        if (suburb.isPresent()) {
            return suburb.get();
        }
        return null;
    }
}
