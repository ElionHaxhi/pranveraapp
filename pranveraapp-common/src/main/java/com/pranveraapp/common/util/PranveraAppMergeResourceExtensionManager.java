package com.pranveraapp.common.util;

import com.pranveraapp.common.extension.ExtensionManager;
import org.springframework.stereotype.Component;

/**
 * Created by elion on 27/02/16.
 */
@Component("elPranveraAppMergeResourceExtensionManager")
public class PranveraAppMergeResourceExtensionManager extends ExtensionManager<PranveraAppMergeResourceExtensionHandler> {

    public PranveraAppMergeResourceExtensionManager(){
        super(PranveraAppMergeResourceExtensionHandler.class);
    }
}
