package com.pranveraapp.core.util;

import com.pranveraapp.core.search.domain.SearchCriteria;

/**
 * Created by elion on 15/02/16.
 */
public class FunctionUtils {

       /**
     * This function was build to retrive the first index to query
     * es if page value is 3 and the maxnumberforresult is 5 the first index will be 10
     *
     * @param searchCriteria
     * @return
     */
    public static int prepareFirstResultCriteria(SearchCriteria searchCriteria){
        if((searchCriteria.getPage()-1)==0)
            return 0;
        return (searchCriteria.getPage()-1)*searchCriteria.getPageSize();
    }
}
