package com.pranveraapp.core.web.processor;

import com.pranveraapp.common.file.StaticAssetPathService;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Similar to but handles href tags.
 * Mainly those that hase a useCdn=true attribute or those that are inside a script tag.
 *
 * Created by elion on 14/02/16.
 */
public class HrefUrlRewriteProcessor  extends UrlRewriteProcessor{

    //@Resource(name = "elStaticAssetPathService")
    //protected StaticAssetPathService staticAssetPathService;

    private static final String LINK = "link";
    private static final String HREF = "href";

    /**
     * Sets the name of this processor to be used in Thymeleaf template
     */
    public HrefUrlRewriteProcessor(){
        super(HREF);
    }

    @Override
    protected Map<String, String> getModifiedAttributeValues(Arguments arguments, Element element, String attributeName){

        Map<String, String> attrs = new HashMap<String,String>();

        String elementName = element.getNormalizedName();
        String useCDN = element.getAttributeValue("useCDN");

        if(LINK.equals(elementName) || (useCDN != null && "true".equals(useCDN))){
            attrs = super.getModifiedAttributeValues(arguments,element,attributeName);
            String srcAttr = attrs.remove("src");
            attrs.put(HREF, srcAttr);
        } else {
            attrs.put(HREF, element.getAttributeValue(attributeName));
        }
        return attrs;
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
