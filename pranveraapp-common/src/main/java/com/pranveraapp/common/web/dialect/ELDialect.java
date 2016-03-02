package com.pranveraapp.common.web.dialect;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.expression.IStandardVariableExpressionEvaluator;
import org.thymeleaf.standard.expression.StandardExpressions;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by elion on 14/02/16.
 */
public class ELDialect extends AbstractDialect {

    private Set<IProcessor> processors = new HashSet<IProcessor>();

    @Resource(name = "elVariableExpressionEvaluator")
    private IStandardVariableExpressionEvaluator expressionEvaluator;

    @Override
    public String getPrefix() {
        return "prv";
    }

    @Override
    public boolean isLenient() {
        return true;
    }

    @Override
    public Set<IProcessor> getProcessors() {
        return processors;
    }

    public void setProcessors(Set<IProcessor> processors) {
        this.processors = processors;
    }

    @Override
    public Map<String, Object> getExecutionAttributes() {
        final Map<String,Object> executionAttributes = new LinkedHashMap<String, Object>();
        executionAttributes.put(StandardExpressions.STANDARD_VARIABLE_EXPRESSION_EVALUATOR_ATTRIBUTE_NAME, expressionEvaluator);
        return executionAttributes;
    }

}
