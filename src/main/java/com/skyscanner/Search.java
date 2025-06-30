package com.skyscanner;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Search class represents the input payload received
 * from the client in a search request.
 *
 * It currently includes only one field: 'city',
 * which is used to filter hotel and rental results.
 */

public class Search {

    /**
     * The name of the city to search for.
     * Mapped from the JSON input using @JsonProperty.
     *
     * Example JSON:
     * {
     *     "city": "rustburg"
     * }
     */
    @JsonProperty
    private String city;

    public Search(){}
    

    //Parameterized constructor for manually creating a Search object.
    public Search(String city){
        this.city=city;
    }

    //getter for city
    public String getCity(){
        return city;
    }
}
