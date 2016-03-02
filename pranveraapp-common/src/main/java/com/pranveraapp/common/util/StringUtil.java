package com.pranveraapp.common.util;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;


/**
 * Created by elion on 07/02/16.
 */
public class StringUtil {


    /**
     * Protect against HTTP Response Splitting
     *
     * @return
     */
    public static String cleanseUrlString(String input) {
        return removeSpecialCharacters(decodeUrl(input));
    }

    public static String decodeUrl(String encodedUrl) {
        try {
            return encodedUrl == null ? null : URLDecoder.decode(encodedUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // this should not happen
            e.printStackTrace();
            return encodedUrl;
        }
    }

    public static String removeSpecialCharacters(String input) {
        if (input != null) {
            input = input.replaceAll("[ \\r\\n]", "");
        }
        return input;
    }




}
