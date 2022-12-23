package com.pranveraapp.core.web.processor;

import com.pranveraapp.common.web.PranveraAppRequestContext;
import com.pranveraapp.core.search.domain.SearchCriteria;
import com.pranveraapp.core.web.util.ProcessorUtils;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.attr.AbstractAttributeModifierAttrProcessor;
import org.thymeleaf.standard.expression.Expression;
import org.thymeleaf.standard.expression.StandardExpressions;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * A Thymeleaf processor tha processes the value attribute on the element it's tied to with
 * a predetermined value on the SearchFacetResultDTO object that is passed into this processor.
 *
 * Created by elion on 14/02/16.
 */
public class PaginationPageLinkProcessor extends AbstractAttributeModifierAttrProcessor {

    /**
     * Sets the name of this processor to be used in thymeleaf template
     */
    public PaginationPageLinkProcessor(){
        super("paginationpagelink");
    }

    @Override
    public int getPrecedence(){
        return 10000;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Map<String ,String> getModifiedAttributeValues(Arguments arguments,Element element, String attributeName){

        Map<String,String> attrs = new HashMap<String,String>();

        PranveraAppRequestContext elContext = PranveraAppRequestContext.getPranveraAppRequestContext();

        HttpServletRequest request = elContext.getRequest();

        String baseUrl = request.getRequestURL().toString();
        Map<String, String[]> params = new HashMap<String,String[]>(request.getParameterMap());

        Expression expression = (Expression)StandardExpressions.getExpressionParser(arguments.getConfiguration())
                .parseExpression(arguments.getConfiguration(),arguments,element.getAttributeValue(attributeName));

        String page = (String) expression.execute(arguments.getConfiguration(), arguments);

        if(!page.equals("...")){
            params.put(SearchCriteria.PAGE_NUMBER, new String[]{page});
        }

        String url = ProcessorUtils.getUrl(baseUrl, params);

        attrs.put("href", url);

        return attrs;
    }

    @Override
    protected ModificationType getModificationType(Arguments arguments, Element element, String attributeName, String
                                                   newAttributeName){
        return ModificationType.SUBSTITUTION;
    }

    @Override
    protected boolean removeAttributeIfEmpty(Arguments arguments, Element element,
                                             String attributeName, String newAttributeName){
        return true;
    }

    @Override
    protected boolean recomputeProcessorsAfterExecution(Arguments arguments, Element element,
                                                        String attributeName){
        return false;
    }

}




















