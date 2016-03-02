package com.pranveraapp.common.web;

import org.springframework.web.context.request.WebRequest;

/**
 * Created by elion on 06/02/16.
 */
public abstract  class AbstractPranveraAppWebRequestProcessor implements PranveraAppWebRequestProcessor {

    public void postProcess(WebRequest request){
        //nada
    }
}
