package com.skyscanner;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * SearchResult represents a single result item (hotel or rental)
 * that matches the user's search query.
 * It includes the city, the name/title of the listing, and its kind (either "hotel" or "rental").
 */

public class SearchResult {

      /**
     * The city where the hotel or rental is located.
     */
    @JsonProperty
    private String city;  

     /**
     * The name of the hotel or rental listing.
     */
    @JsonProperty
    private String title;

    /**
     * The type of result, either "hotel" or "rental".
     * This is not present in the JSON file, but is set manually after deserialization.
     */
    @JsonProperty
    private String kind;


    
    /**
     * Default constructor needed for Jackson deserialization.
     */
    public SearchResult(){}


    //Parameterized constructor used for manual creation of result objects.
    public SearchResult(String city,String title,String kind){
        this.city=city;
        this.title=title;
        this.kind=kind;
    }

    public String getCity(){
        return city;
    }
    public String getTitle(){
        return title;
    }
    public String getKind(){
        return kind;
    }
    public void setKind(String kind){
        this.kind=kind;
    }
    
}
