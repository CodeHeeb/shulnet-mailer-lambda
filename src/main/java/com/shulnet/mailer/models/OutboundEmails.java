package com.shulnet.mailer.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("outboundemails")
public class OutboundEmails {

	@Id
	private String _id;
	
	private String email_subject;
	
	private String email_html_body;
	
	private String email_text_body;
	
	private String portal_id;
	
	private Boolean is_mass_email;
	
	private String email_to;
	
	private Date send_date;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	public OutboundEmails(String _id, String email_subject, String email_html_body, String email_text_body, String portal_id, Date send_date, Date createdAt, Date updatedAt, Boolean is_mass_email, String email_to)
	{
		this._id = _id;
		this.email_subject = email_subject;
		this.email_html_body = email_html_body;
		this.email_text_body = email_text_body;
		this.portal_id = portal_id;
		this.send_date = send_date;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.is_mass_email = is_mass_email;
		this.email_to = email_to;
	}
	
	@Override
	public String toString() {
		return String.format(
				"{ '_id' : '%s', 'email_subject': '%s', 'email_html_body': '%s', 'email_text'body': '%s', 'portal_id': '%s', 'send_date': '%s', 'createdAt': '%s', 'updatedAt': '%s', 'is_mass_email': %s, 'email_to': '%s' }",
				_id, email_subject, email_html_body, email_text_body, portal_id, send_date, createdAt, updatedAt, is_mass_email, email_to);
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getEmail_subject() {
		return email_subject;
	}

	public void setEmail_subject(String email_subject) {
		this.email_subject = email_subject;
	}

	public String getEmail_html_body() {
		return email_html_body;
	}

	public void setEmail_html_body(String email_html_body) {
		this.email_html_body = email_html_body;
	}

	public String getEmail_text_body() {
		return email_text_body;
	}

	public void setEmail_text_body(String email_text_body) {
		this.email_text_body = email_text_body;
	}

	public String getPortal_id() {
		return portal_id;
	}

	public void setPortal_id(String portal_id) {
		this.portal_id = portal_id;
	}

	public Date getSend_date() {
		return send_date;
	}

	public void setSend_date(Date send_date) {
		this.send_date = send_date;
	}
	
	public Date getCreatedAt()
	{
		return createdAt;
	}
	
	public void setCreatedAt(Date createdAt)
	{
		this.createdAt = createdAt;
	}
	
	public Date getUpdatedAt()
	{
		return updatedAt;
	}
	
	public void setUpdatedAt(Date updatedAt)
	{
		this.updatedAt = updatedAt;
	}
	
	public Boolean getIsMassEmail()
	{
		return is_mass_email;
	}
	
	public void setIsMassEmail(Boolean is_mass_email)
	{
		this.is_mass_email = is_mass_email;
	}
	
	public String getEmailTo()
	{
		return email_to;
	}
	
	public void setEmailTo(String email_to)
	{
		this.email_to = email_to;
	}
	
}
