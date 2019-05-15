package com.nurturecloud;

import com.nurturecloud.model.Suburb;
import com.nurturecloud.service.SuburbFinderService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 * Referenced Java in-memory geospatial indexing from https://guzalexander.com/2018/11/04/java-in-memory-geospatial.html
 */
public class App {
    public static void main(String[] args) throws IOException {
        try (BufferedReader command = new BufferedReader(new InputStreamReader(System.in))) {

            boolean running = true;

            SuburbFinderService suburbFinder = new SuburbFinderService();
            while (running) {

                System.out.print("\nPlease enter a suburb name: ");
                String suburbName = command.readLine();

                if(suburbName.equals("")) {
                    running = false;
                    break;
                }
                System.out.print("Please enter the postcode: ");
                Long postcode = Long.parseLong(command.readLine());

                Map<String, List<Suburb>> suburbsMap = suburbFinder.findNearbySuburbs(postcode, suburbName);

                suburbsMap.keySet().forEach(key -> {
                    List<Suburb> suburbs = suburbsMap.get(key);
                    if (suburbs != null && suburbs.size() != 0) {

                        System.out.println("\n\n" + key + " Suburbs:");
                        suburbs.stream().forEach(suburb -> {
                            System.out.println(String.format("\r %s %s", suburb.getLocality(), suburb.getPcode()));
                        });

                    } else {
                        System.out.println(String.format("Nothing found for %s, %s!!\n", suburbName, postcode));
                    }
                });

            }
        }
    }
}
