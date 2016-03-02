package com.pranveraapp.common.file;

/**
 * Created by elion on 14/02/16.
 */
public interface StaticAssetPathService {

    public String convertAssetPath(String assetPath, String contextPath, boolean secureRequest);
}
