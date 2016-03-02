package com.pranveraapp.common.web;

import org.springframework.web.context.request.WebRequest;

/**
 * Created by elion on 06/02/16.
 */
public interface PranveraAppWebRequestProcessor {

    public void process(WebRequest request);
    public void postProcess(WebRequest request);
}
