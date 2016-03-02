package com.pranveraapp.core.web.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Utility class for Thymeleaf Processor
 *
 * Created by elion on 14/02/16.
 */
public class ProcessorUtils {

    /**
     * Gets a UTF-8 URL encoded URL based on the current URL as well as the specified map
     * of query string parameters
     *
     */
    public static String getUrl(String baseUrl, Map<String, String[]> parameters) {

        if (baseUrl.contains("?")) {
            throw new IllegalArgumentException("baseUrl contained a ? indicating it is not a base url");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(baseUrl);

        boolean atLeastOneParam = false;

        if (parameters != null && parameters.size() > 0) {
            for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
                if (entry.getValue().length > 0) {
                    atLeastOneParam = true;
                }
            }
        }
        if (atLeastOneParam) {
            sb.append("?");
        } else {
            return sb.toString();
        }

        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            String key = entry.getKey();
            for (String value : entry.getValue()) {
                StringBuilder parameter = new StringBuilder();
                try {
                    parameter.append(URLEncoder.encode(key, "UTF-8"));
                    parameter.append("=");
                    parameter.append(URLEncoder.encode(value, "UTF-8"));
                    parameter.append("&");
                } catch (UnsupportedEncodingException e) {
                    parameter = null;
                }
                sb.append(parameter);
            }
        }

        String url = sb.toString();
        if(url.charAt(url.length() -1) == '&'){
            url = url.substring(0,url.length()-1);
        }
        return url;
    }

}
















