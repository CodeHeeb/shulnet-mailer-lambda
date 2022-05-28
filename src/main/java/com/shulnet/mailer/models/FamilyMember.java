package com.shulnet.mailer.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("memberfamilies")
public class FamilyMember {
	
	@Id
	private String _id;
	
	@Field("first_name")
	private String firstName;
	
	@Field("last_name")
	private String lastName;
	
	private String email;
	
	@Field("email_optout_date")
	private Date emailOptoutDate;
	
	@Field("member_id")
	private String memberId;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	
	public String getMemberId()
	{
		return memberId;
	}
	
	public void setMemberId(String memberId)
	{
		this.memberId = memberId;
	}
}
