package com.skyscanner;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.util.*;


public class HoenScannerApplication extends Application<HoenScannerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new HoenScannerApplication().run(args);
    }

    @Override
    public String getName() {
        return "hoen-scanner";
    }

    @Override
    public void initialize(final Bootstrap<HoenScannerConfiguration> bootstrap) {
           //No special initialization needed
    }
    
     /**
     * The core logic of the app — loads data and registers the search resource.
     */
    @Override
    public void run(final HoenScannerConfiguration configuration, final Environment environment) {
        ObjectMapper mapper=new ObjectMapper();

        try{
            //Load hotel data from JSON file
            List<SearchResult> hotels=mapper.readValue(
                new File("src/main/resources/hotels.json"),
                new TypeReference<List<SearchResult>>() {});

            //Add label to identify them as hotels
            hotels.forEach(r-> r.setKind("rental"));

            //load rental data from JSON file
            List<SearchResult> rentals=mapper.readValue(
                new File("src/main/resources/rental_cars.json"),
                new TypeReference<List<SearchResult>>() {});

            //Add label to identify them as rentals
            rentals.forEach(r-> r.setKind("rental"));
            
            //combine both hotel and rental results into one list
            List<SearchResult> allResults=new ArrayList<>();
            allResults.addAll(hotels);
            allResults.addAll(rentals);

            //Register the SearchResource class with the combined list
            environment.jersey().register(new SearchResources(allResults));

            //Success message
            System.out.println("Welcome to Hoen Scanner!");
        }catch(IOException e){
             //print error
             System.err.println("Failed to load search data: " + e.getMessage());
        }
    }

}
