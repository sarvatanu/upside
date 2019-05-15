package com.nurturecloud.service;

import com.nurturecloud.model.Suburb;

import java.util.List;
import java.util.Map;

public interface FinderService {

    Map<String, List<Suburb>> findNearbySuburbs(final Long postcode,
                                                final String locality);

}
