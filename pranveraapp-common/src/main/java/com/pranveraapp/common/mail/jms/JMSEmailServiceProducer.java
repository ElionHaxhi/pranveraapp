package com.pranveraapp.common.mail.jms;
import com.pranveraapp.common.mail.service.message.EmailServiceProducer;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;
public interface JMSEmailServiceProducer {
	 /**
     * @return the emailServiceTemplate
     */
    public JmsTemplate getEmailServiceTemplate();

    /**
     * @param emailServiceTemplate the emailServiceTemplate to set
     */
    public void setEmailServiceTemplate(JmsTemplate emailServiceTemplate);

    /**
     * @return the emailServiceDestination
     */
    public Destination getEmailServiceDestination();

    /**
     * @param emailServiceDestination the emailServiceDestination to set
     */
    public void setEmailServiceDestination(Destination emailServiceDestination);
}
