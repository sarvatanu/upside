package com.nurturecloud.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.nurturecloud.model.Suburb;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This static class is to load the suburb details on start up using GSON.
 * In realworld Suburb should be loaded from datasource.
 * <p>
 * Ideally this service should return the nearby suburbs from GEO code aware datasorce.
 */
public class SuburbLoaderService {
    private static final Logger logger = Logger.getLogger(SuburbLoaderService.class.getName());
    private static final String SUBURB_DATA = "/aus_suburbs.json";
    private static final InputStream SUBURB_DATA_STREAM = SuburbLoaderService.class.getClass().getResourceAsStream(SUBURB_DATA);
    private static List<Suburb> suburbs = new ArrayList<>();

    static {
        long startTime, endTime;
        startTime = System.currentTimeMillis();

        loadSuburbs(SUBURB_DATA_STREAM);

        endTime = System.currentTimeMillis();
        logger.log(Level.INFO, "Time Taken to load : " + (endTime - startTime) + "ms");
    }

    private static void loadSuburbs(InputStream inputStream) {
        logger.log(Level.INFO, "laoding suburb details.");
        try {
            JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
            Gson gson = new GsonBuilder().create();
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                Suburb suburb = gson.fromJson(jsonReader, Suburb.class);
                if (suburb.getLatitude() != null && suburb.getLongitude() != null) {
                    suburbs.add(suburb);
                }
            }
            jsonReader.endArray();
            jsonReader.close();
        } catch (UnsupportedEncodingException ex) {
            logger.log(Level.SEVERE, "UnsupportedEncodingException raised :", ex);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "IOException raised :", ex);
        }
    }

    public List<Suburb> getSuburbs() {
        return suburbs;
    }
}
