package com.pranveraapp.common.mail.dao;

import com.pranveraapp.common.mail.domain.EmailTarget;
import com.pranveraapp.common.mail.domain.EmailTracking;

public interface EmailReportingDao {
	public Long createTracking(String emailAddress, String type, String extraValue) ;
    public void recordOpen(Long emailId, String userAgent);
    public void recordClick(Long emailId, String customerId, String destinationUri, String queryString);
    public EmailTracking retrieveTracking(Long emailId);
    public EmailTarget createTarget();
}
