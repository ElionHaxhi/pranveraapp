package com.pranveraapp.common.extensibility.context.merge.handlers;

/**
 * @author elion
 */
public class CommaDelimitedNodeValueMerge extends NodeValueMerge {

    @Override
    public String getDelimiter() {
        return ",";
    }

    @Override
    public String getRegEx() {
        return getDelimiter();
    }
}
