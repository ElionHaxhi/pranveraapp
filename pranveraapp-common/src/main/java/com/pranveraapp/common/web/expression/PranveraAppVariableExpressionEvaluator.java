package com.pranveraapp.common.web.expression;

import org.thymeleaf.context.IProcessingContext;
import org.thymeleaf.spring4.expression.SpelVariableExpressionEvaluator;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by elion on 14/02/16.
 */

public class PranveraAppVariableExpressionEvaluator extends SpelVariableExpressionEvaluator {

    @Resource(name = "elVariableExpressions")
    protected List<PranveraAppVariableExpression> expressions = new ArrayList<PranveraAppVariableExpression>();

    @Override
    protected Map<String,Object> computeAdditionalExpressionObjects(final IProcessingContext processingContext) {
        Map<String, Object> map = new HashMap<String, Object>();

        for (PranveraAppVariableExpression expression : expressions) {
            if (!(expression instanceof NullPranveraAppVariableExpression)) {
                map.put(expression.getName(), expression);
            }
        }

        return map;
    }

}
