package com.pranveraapp.common.web;

import com.pranveraapp.common.classloader.release.ThreadLocalManager;
import com.pranveraapp.common.locale.domain.Locale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by elion on 02/02/16.
 */
public class PranveraAppRequestContext {

    protected static final Log LOG = LogFactory.getLog(PranveraAppRequestContext.class);

    private static final ThreadLocal<PranveraAppRequestContext> PRANVERAAPP_REQUEST_CONTEXT =
            ThreadLocalManager.createThreadLocal(PranveraAppRequestContext.class);

    public static PranveraAppRequestContext getPranveraAppRequestContext(){
        return PRANVERAAPP_REQUEST_CONTEXT.get();
    }

    public static void setPranveraappRequestContext(PranveraAppRequestContext pranveraappRequestContext){
        PRANVERAAPP_REQUEST_CONTEXT.set(pranveraappRequestContext);
    }

    public static boolean hasLocale(){
        // for the moment return always false
        return false;
    }

    public static boolean hasCurrency(){
        // as now return always false
        return false;
    }

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected java.util.Locale javaLocale;
    protected WebRequest webRequest;
    protected Locale locale;
    protected Map<String,Object> additionalProperties = new HashMap<String,Object>();

    /**
     * Gets the current request on the context
     * @return
     */
    public HttpServletRequest getRequest(){
        return request;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
        this.javaLocale = convertLocaleToJavaLocale();
    }

    /** Sets the current request on the context. Noe that this also invokes {@link #setWebRequest(WebRequest)}
     *  by wrapping
     *  <b>request</b> in a {@link ServletWebRequest}.
     *
     *  @param request
     */
    public void setRequest(HttpServletRequest request){
        this.request = request;
        this.webRequest = new ServletWebRequest(request);
    }

    public java.util.Locale getJavaLocale() {
        if (this.javaLocale == null) {
            this.javaLocale = convertLocaleToJavaLocale();
        }
        return this.javaLocale;
    }

    protected java.util.Locale convertLocaleToJavaLocale() {
        if (locale == null || locale.getLocaleCode() == null) {
            return java.util.Locale.getDefault();
        } else {
            return PranveraAppRequestContext.convertLocaleToJavaLocale(locale);
        }
    }

    public static java.util.Locale convertLocaleToJavaLocale(Locale broadleafLocale) {
        if (broadleafLocale != null) {
            String localeString = broadleafLocale.getLocaleCode();
            return org.springframework.util.StringUtils.parseLocaleString(localeString);
        }
        return null;
    }

    /**
     * Returns the response for the context
     * @return
     */
    public HttpServletResponse getResponse(){
        return response;
    }

    /**
     * Sets the response for the context
     *
     * @param response
     */
    public  void setResponse(HttpServletResponse response){
        this.response = response;
    }

    /**
     * Sets the generic request on the context. This is available to be used in non-Servlet (like Portlets).
     * Note that if <b>webRequest</b> is an instance of {@link ServletWebRequest} then
     * {@link #setRequest(HttpServletRequest)} will be invoked as well with the native undelying
     * {@link HttpServletRequest}
     * passed as a parameter.
     * <br/>
     * <br/>
     * Also, if <b>webRequest</b> is an instance of {@link ServletWebRequest} then an attempt is made to set the response
     * (note that this could be null if the ServletWebRequest wasnot instantiated with both the {@link HttpServletResponse})
     * @param webRequest
     */
    public void setWebRequest(WebRequest webRequest){
        this.webRequest = webRequest;
        if(webRequest instanceof ServletWebRequest){
            this.request = ((ServletWebRequest)webRequest).getRequest();
            setResponse(((ServletWebRequest)webRequest).getResponse());
        }
    }

    /**
     * Returns the generic request for use outside of servlets (like in Portlets). This will be automatically set by invoking
     *  {@link #setRequest(HttpServletRequest)}
     * @return the generic request
     * @see {@link #setWebRequest(WebRequest)}
     */
    public WebRequest getWebRequest(){
        return webRequest;
    }

    public String getRequestURIWithoutContext(){
        String requestURIWithoutContext =null;

        if(request !=null && request.getRequestURI()!=null){
            if(request.getContextPath() !=null){
                requestURIWithoutContext = request.getRequestURI().substring(request.getContextPath().length());
            }
            else {
                requestURIWithoutContext = request.getRequestURI();
            }

            //Remove JSESSION-ID or other modifiers
            int pos = requestURIWithoutContext.indexOf(";");
            if(pos >= 0){
                requestURIWithoutContext = requestURIWithoutContext.substring(0,pos);
            }
        }
        return requestURIWithoutContext;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}

























