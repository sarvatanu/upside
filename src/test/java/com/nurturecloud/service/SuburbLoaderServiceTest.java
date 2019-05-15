package com.nurturecloud.service;

import com.nurturecloud.model.Suburb;
import org.junit.Test;

public class SuburbLoaderServiceTest {

    @Test
    public void testLoadSuburbsShouldLoadReturnedSuburbsFromFile() {
        SuburbLoaderService suburbLoaderService = new SuburbLoaderService();
        assert suburbLoaderService.getSuburbs().isEmpty() == false;

        Suburb suburb = suburbLoaderService.getSuburbs().get(1);
        assert suburb.getPcode() != null;
        assert suburb.getLocality() != null;
        assert suburb.getState() != null;
        assert suburb.getCategory() != null;
        assert suburb.getComments() != null;
        assert suburb.getLatitude() != null;
        assert suburb.getLongitude() != null;

        suburb = suburbLoaderService.getSuburbs().get(0);
        assert suburb.getPcode() != null;
        assert suburb.getLocality() != null;
        assert suburb.getState() != null;
        assert suburb.getCategory() != null;
        assert suburb.getComments() != null;
        assert suburb.getLatitude() == null;
        assert suburb.getLongitude() == null;
    }
}
