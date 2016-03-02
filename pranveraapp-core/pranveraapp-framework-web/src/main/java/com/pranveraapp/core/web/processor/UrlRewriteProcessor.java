package com.pranveraapp.core.web.processor;

import com.pranveraapp.common.file.StaticAssetPathService;
import com.pranveraapp.common.web.PranveraAppRequestContext;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.attr.AbstractAttributeModifierAttrProcessor;
import org.thymeleaf.standard.expression.Expression;
import org.thymeleaf.standard.expression.StandardExpressions;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * A thymeleaf processor that process the given url through the StaticAssetServcie's
 * {@link StaticAssetService#convertAssetPath(String, String, boolean)} method to determine
 * the appropriate URL for the asset to be served from.
 *
 * Created by elion on 14/02/16.
 */
public class UrlRewriteProcessor extends AbstractAttributeModifierAttrProcessor {

    //@Resource(name = "elStaticAssetPathService")
    //protected StaticAssetPathService staticAssetPathService;

    /**
     * Set the name of this processor to be used in Thymeleaf template
     */
    public UrlRewriteProcessor(){
        this("src");
    }

    protected UrlRewriteProcessor(final String attributeName){
        super(attributeName);
    }

    @Override
    public int getPrecedence(){
        return 1000;
    }

    /**
     * Return true if the request scheme is equal to HTTPS
     * @param request
     * @return
     */
    protected boolean isRequestSecure(HttpServletRequest request){
        return ("HTTPS".equalsIgnoreCase(request.getScheme()) || request.isSecure());
    }

    @Override
    protected Map<String,String> getModifiedAttributeValues(Arguments arguments, Element element, String attributeName){

        Map<String ,String> attrs = new HashMap<String, String>();
        HttpServletRequest request = PranveraAppRequestContext.getPranveraAppRequestContext().getRequest();

        boolean secureRequest = true;

        if(request !=null){
            secureRequest = isRequestSecure(request);
        }

        String elementValue = element.getAttributeValue(attributeName);

        if(elementValue.startsWith("/")){
            elementValue = "@{ " + elementValue + " }";
        }
        Expression expression = (Expression) StandardExpressions.getExpressionParser(arguments.getConfiguration())
                .parseExpression(arguments.getConfiguration(), arguments, elementValue);
        String assetPath = (String)expression.execute(arguments.getConfiguration(), arguments);

        //We are forcing an evaluation of @{} from Thymeleaf above which will
        //automatically add a contextPath, no need to add it twice
        //assetPath = staticAssetPathService.convertAssetPath(assetPath, null, secureRequest);

        attrs.put("src", "/imgsrc");

        return  attrs;
    }
    @Override
    protected ModificationType getModificationType(Arguments arguments, Element element, String attributeName, String newAttributeName) {
        return ModificationType.SUBSTITUTION;
    }

    @Override
    protected boolean removeAttributeIfEmpty(Arguments arguments, Element element, String attributeName, String newAttributeName) {
        return true;
    }

    @Override
    protected boolean recomputeProcessorsAfterExecution(Arguments arguments, Element element, String attributeName) {
        return false;
    }


}





























