package com.pranveraapp.common.extensibility.context.merge.handlers;
import org.w3c.dom.Node;
import java.util.List;

/**
 * Created by elion on 25/01/16.
 */
public interface MergeHandler {
    public Node[] merge(List<Node> nodeList1, List<Node> nodeList2, List<Node> exhaustedNodes);

    /**
     * Retrieve the priority for the handler. Priorities are used by the MergeManager
     * to establish the order of operations for performing merges.
     *
     * @return the priority value
     */
    public int getPriority();

    /**
     * Set the priority for this handler
     * @param priority
     */
    public void setPriority(int priority);

    /**
     * Retrieve the XPath query associated with this handler. XPath is used by the handler
     * to define to section of the source and patch documents that will be merged.
     *
     * @return the xpath query
     */
    public String getXPath();

    /**
     * Set the xpath query
     *
     * @param xpath
     */
    public void setXPath(String xpath);

    /**
     * Retrieve any child merge handlers associated with this handler. Child merge handlers
     * may be added alter merge behavior for a subsection of the merge area defined
     * by this merge handler.
     *
     * @return child merge handlers
     */
    public MergeHandler[] getChildren();

    /**
     * Set the child merge handlers
     *
     * @param children
     */
    public void setChildren(MergeHandler[] children);

    /**
     * Retrieve the name associated with this merge handlers. Merge handler names are
     * period-delimited numeric strings that define the hierarchical relationship of mergehandlers
     * and their children. For example, "2" could be used to define the second handler in the configuration
     * list and "2.1" would be the name describing the first child handler of "2".
     *
     * @return the period-delimited numeric string that names this handler
     */
    public String getName();

    /**
     * Set the period-delimited numeric string that names this handler
     *
     * @param name
     */
    public void setName(String name);

}
