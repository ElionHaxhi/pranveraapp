package com.pranveraapp.common.extensibility.context.merge.handlers;

/**
 * @author elion
 */
public class SpaceDelimitedNodeValueMerge extends NodeValueMerge {

    @Override
    public String getDelimiter() {
        return " ";
    }
}
