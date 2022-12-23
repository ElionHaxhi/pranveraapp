package com.pranveraapp.core.web.service;

import com.pranveraapp.common.util.ELSystemProperty;
import com.pranveraapp.core.search.domain.SearchCriteria;
import com.pranveraapp.core.search.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by elion on 03/02/16.
 */
@Service("elSearchFacetDTOService")
public class SearchFacetDTOServiceImpl implements SearchFacetDTOService {

    /**
     * Set the size of numbers of posts to displey per page
     *
     * @return
     */
    protected int getDefaultPageSize(){
        return 3;
    }

    protected int getMaxPageSize(){
        return 100;
    }

    @Override
    @SuppressWarnings("unchecked")
    public SearchCriteria buildSearchCriteria(HttpServletRequest request){
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setPageSize(getDefaultPageSize());

        Map<String, String[]> facets = new HashMap<String,String[]>();

        for(Iterator<Map.Entry<String,String[]>> iter = request.getParameterMap().entrySet().iterator(); iter.hasNext();)
        {
            Map.Entry<String, String[]> entry = iter.next();
            String key = entry.getKey();

            if(key.equals(SearchCriteria.PAGE_NUMBER)){
                searchCriteria.setPage(Integer.parseInt(entry.getValue()[0]));
            }
        }
        searchCriteria.setFilterCriteria(facets);
        return searchCriteria;
    }

    /*@Override
    @SuppressWarnings("unchecked")
    public void setActiveFacetResults(List<SearchFacetDTO> facets, HttpServletRequest request){
        if(facets !=null){
            for(SearchFacetDTO facet: facets){
                for(SearchFacetResultDTO facetResult : facet.getFacetValues()){
                    facetReulst.setActive(isActive(facetResult,request));
                }
            }
        }
    }
*/
    /*@Override
    @SuppressWarnings("unchecked")
    public boolean isActive(SearchFacetResultDTO result, HttpServletRequest request){
        Map<String, String[]> params = request.getParameterMap();

        for(Map.Entry<String, String[]> entry: params.entrySet()){
            String key = entry.getKey();
            if(key.equals(getUrlKey(result))){
                for(String val : entry.getValue()){
                    if(val.equals(getValue(result))){
                        return true;
                    }
                }
            }
        }
        return false;
    }


    @Override
    public String getUrlKey(SearchFacetResultDTO result){
        return result.getFacet().getField().getAbbreviation();
    }

    @Override
    public String getValue(SearchFacetResultDTO result){
        return result.getValueKey();
    }

    @Override
    public String getUrlKey(SearchFacetDTO result){
        return result.getFacet().getField().getAbbreviation();
    }
*/






















}
