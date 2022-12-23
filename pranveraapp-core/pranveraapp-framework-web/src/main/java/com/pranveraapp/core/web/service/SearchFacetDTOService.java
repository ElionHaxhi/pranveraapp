package com.pranveraapp.core.web.service;

import com.pranveraapp.core.search.domain.SearchCriteria;

import javax.servlet.http.HttpServletRequest;

/**
 * Provides methods that facilitate interaction with SearchFacetDTO and SearchFacetResultDTOs
 * Created by elion on 04/02/16.
 */
public interface SearchFacetDTOService {
    /**
     * Given a servlet request and a list of available facets for this request (could be search or category based),
     * this method will build out a SearchCriteria object to be used By PostSearchService.
     * It will perform translation from query string parameters to the SearchCriteria.
     *
     * @param request
     * @param availableFacets
     * @return the SearchCiteria
     */
    public SearchCriteria buildSearchCriteria(HttpServletRequest request);

    /**
     * Sets the "active" boolena on a given SearchFacetResult as determinated by the current request
     * @param facets
     * @param request
     */
   // public void setActiveFacetResults(List<SearchFacetDTO> facets, HttpServletRequest request);

    /**
     * Returns whether or not the SearchFacetResultDTO's key /value pair is present in the servlet request
     *
     * @param result
     * @param request
     * @return
     */
   // public boolean isActive(SearchFacetResultDTO result, HttpServletRequest request);

    /**
     * Gets the url abbreviation associated with a given SearchFacetResultDTO
     * @param result
     * @return
     */
   // public String getUrlKey(SearchFacetResultDTO result);

    /**
     * Gets the url abbreviation associated with a given SearchFacetDTO
     * @param result
     * @return
     */
   // public String getUrlKey(SearchFacetDTO result);

    /**
     * Gets the value of the given SearchFacetResultDTO.
     * The default PravneraApp implementation will return the String value of the result if the value
     * is not empty or "range[<min-value>:<max-value>]" if the value was empty.
     * @param result
     * @return
     */
   // public String getValue(SearchFacetResultDTO result);

}
