package com.pranveraapp.common.web;

import com.pranveraapp.common.i18n.service.TranslateService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.thymeleaf.Arguments;
import org.thymeleaf.messageresolver.AbstractMessageResolver;
import org.thymeleaf.messageresolver.MessageResolution;
import org.thymeleaf.util.Validate;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * Created by elion on 28/02/16.
 */
public class PranveraAppThymeleafMessageResolver extends AbstractMessageResolver {
        protected static final Log LOG = LogFactory.getLog(PranveraAppThymeleafMessageResolver.class);
    protected static final String I18N_VALUE_KEY = "translate";

    @Resource(name = "elTranslateService")
    protected TranslateService translateService;

    /**
     * Resolve a translated value of an object's property.
     *
     * @param args
     * @param key
     * @param messageParams
     * @return the resolved message
     */
    public MessageResolution resolveMessage(final Arguments args, final String key, final Object[] messageParams) {
        Validate.notNull(args, "args cannot be null");
        Validate.notNull(args.getContext().getLocale(), "Locale in context cannot be null");
        Validate.notNull(key, "Message key cannot be null");

        if (I18N_VALUE_KEY.equals(key)) {
            Object entity = messageParams[0];
            String property = (String) messageParams[1];
            Locale locale = args.getContext().getLocale();

            if (LOG.isTraceEnabled()) {
                LOG.trace(String.format("Attempting to resolve translated value for object %s, property %s, locale %s",
                        entity, property, locale));
            }

            String resolvedMessage = translateService.getTranslatedValue(entity, property, locale);

            if (StringUtils.isNotBlank(resolvedMessage)) {
                return new MessageResolution(resolvedMessage);
            }
        }

        return null;
    }

}
