package com.pranveraapp.core.web.controller.news.support;

import com.pranveraapp.core.search.domain.SearchCriteria;
import com.pranveraapp.core.search.domain.SearchResult;

import java.util.List;

/**
 * Created by elion on 11/02/16.
 */
public class PaginationInfo {

    private final Integer currentPage;
    private final Integer totalPages;

    public PaginationInfo(SearchResult searchResult,SearchCriteria searchCriteria){
        currentPage = searchCriteria.getPage();
        totalPages = searchResult.getTotalPages();

    }

     public boolean isVisible() {
        return isPreviousVisible() || isNextVisible();
    }

    public boolean isPreviousVisible() {
        return currentPage > 1;
    }

    public boolean isNextVisible() {
        return currentPage < totalPages;
    }

    public long getNextPageNumber() {
        return currentPage + 1;
    }

    public long getPreviousPageNumber() {
        return currentPage - 1;
    }

    public List<PageElement> getPageElements() {
        return new PageElementsBuilder(currentPage, totalPages).build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        PaginationInfo that = (PaginationInfo) o;

        if (currentPage != that.currentPage)
            return false;
        if (totalPages != that.totalPages)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (currentPage ^ (currentPage >>> 32));
        result = 31 * result + (int) (totalPages ^ (totalPages >>> 32));
        return result;
    }
}
