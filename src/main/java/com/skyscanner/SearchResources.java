package com.skyscanner;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

/*
 * REST resources that handles POST requests to /search
 * Filters and returns a list of hotels and rental cars
 * that match the requested city
 */

 @Path("/search")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SearchResources {
    

    //All loaded results (hotels + rentals) from both JSON files
    private final List<SearchResult> allResults;

    /**
     * Constructor to inject the list of search results
     * 
     * @param allResults list of all hotels and rental search results
     */
  public SearchResources(List<SearchResult> allResults){
       this.allResults=allResults;
  }
      /**
     * POST endpoint to handle search requests.
     *
     * @param search input JSON object with city
     * @return filtered list of SearchResult objects matching the city
     */
    @POST
    public List<SearchResult> search (Search search){
       return allResults.stream().filter(result -> result.getCity().equalsIgnoreCase(search.getCity())).collect(Collectors.toList()); 
    }
}
