package com.pranveraapp.common.mail.service.message;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.pranveraapp.common.mail.service.info.EmailInfo;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Map;
public class NullMessageCreator extends MessageCreator {
    
    private static final Log LOG = LogFactory.getLog(NullMessageCreator.class);
    
    public NullMessageCreator(JavaMailSender mailSender) {
        super(mailSender);  
    }
    
    @Override
    public String buildMessageBody(EmailInfo info, Map<String,Object> props) {
        return info.getEmailTemplate();
    }
    
    @Override
    public void sendMessage(final Map<String,Object> props) throws MailException {
        LOG.warn("NullMessageCreator is defined -- specify a real message creator to send emails");
    }
    
}