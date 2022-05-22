package com.shulnet.mailer.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("members")
public class Member {
	
	@Id
	private String _id;
	
	private String first_name;
	
	private String last_name;
	
	private String email;
	
	@Field("email_optout_date")
	private Date emailOptoutDate;
	
	@Field("portal_id")
	private String portalId;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getEmailOptoutDate() {
		return emailOptoutDate;
	}

	public void setEmailOptoutDate(Date emailOptoutDate) {
		this.emailOptoutDate = emailOptoutDate;
	}
	
	public String getPortalId()
	{
		return portalId;
	}
	
	public void setPortal_id(String portal_id)
	{
		this.portalId = portal_id;
	}
}
