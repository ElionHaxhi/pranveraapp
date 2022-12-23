package com.pranveraapp.common.extension;

import java.io.Serializable;

/**
 * Created by elion on 25/02/16.
 */
public class StandardCacheItem implements Serializable {

    private Object cacheItem;
    private ItemStatus itemStatus;

    public Object getCacheItem() {
        return cacheItem;
    }

    public void setCacheItem(Object cacheItem) {
        this.cacheItem = cacheItem;
    }

    public ItemStatus getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(ItemStatus itemStatus) {
        this.itemStatus = itemStatus;
    }

}
