package com.pranveraapp.common.mail.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Parameter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "EL_EMAIL_TRACKING")
public class EmailTrackingImpl implements EmailTracking {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "EmailTrackingId")
	@GenericGenerator(name = "EmailTrackingId", strategy = "com.aziendaline.common.persistence.IdOverrideTableGenerator", parameters = {
			@Parameter(name = "segment_value", value = "EmailTrackingImpl"),
			@Parameter(name = "entity_name", value = "com.aziendaline.common.email.domain.EmailTrackingImpl") })
	@Column(name = "EMAIL_TRACKING_ID")
	protected Long id;

	@Column(name = "EMAIL_ADDRESS")
	@Index(name = "EMAILTRACKING_INDEX", columnNames = { "EMAIL_ADDRESS" })
	protected String emailAddress;

	@Column(name = "DATE_SENT")
	protected Date dateSent;

	@Column(name = "TYPE")
	protected String type;

	@OneToMany(mappedBy = "emailTracking", targetEntity = EmailTrackingClicksImpl.class)
	protected Set<EmailTrackingClicks> emailTrackingClicks = new HashSet<EmailTrackingClicks>();

	@OneToMany(mappedBy = "emailTracking", targetEntity = EmailTrackingOpensImpl.class)
	protected Set<EmailTrackingOpens> emailTrackingOpens = new HashSet<EmailTrackingOpens>();

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getEmailAddress() {
		return emailAddress;
	}

	@Override
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public Date getDateSent() {
		return dateSent;
	}

	@Override
	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the emailTrackingClicks
	 */
	public Set<EmailTrackingClicks> getEmailTrackingClicks() {
		return emailTrackingClicks;
	}

	/**
	 * @param emailTrackingClicks
	 *            the emailTrackingClicks to set
	 */
	public void setEmailTrackingClicks(Set<EmailTrackingClicks> emailTrackingClicks) {
		this.emailTrackingClicks = emailTrackingClicks;
	}

	/**
	 * @return the emailTrackingOpens
	 */
	public Set<EmailTrackingOpens> getEmailTrackingOpens() {
		return emailTrackingOpens;
	}

	/**
	 * @param emailTrackingOpens
	 *            the emailTrackingOpens to set
	 */
	public void setEmailTrackingOpens(Set<EmailTrackingOpens> emailTrackingOpens) {
		this.emailTrackingOpens = emailTrackingOpens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateSent == null) ? 0 : dateSent.hashCode());
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((emailTrackingClicks == null) ? 0 : emailTrackingClicks.hashCode());
		result = prime * result + ((emailTrackingOpens == null) ? 0 : emailTrackingOpens.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!getClass().isAssignableFrom(obj.getClass()))
			return false;
		EmailTrackingImpl other = (EmailTrackingImpl) obj;

		if (id != null && other.id != null) {
			return id.equals(other.id);
		}

		if (dateSent == null) {
			if (other.dateSent != null)
				return false;
		} else if (!dateSent.equals(other.dateSent))
			return false;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		if (emailTrackingClicks == null) {
			if (other.emailTrackingClicks != null)
				return false;
		} else if (!emailTrackingClicks.equals(other.emailTrackingClicks))
			return false;
		if (emailTrackingOpens == null) {
			if (other.emailTrackingOpens != null)
				return false;
		} else if (!emailTrackingOpens.equals(other.emailTrackingOpens))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}
