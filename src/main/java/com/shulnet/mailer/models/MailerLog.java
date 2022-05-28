package com.shulnet.mailer.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("mailerlog")
public class MailerLog {
	
	@Id
	private String _id;
	
	@Field("outboundemails_id")
	private String outboundeMailId;
	
	@Field("mailgun_message")
	private String mailgunMessage;
	
	@Field("mailgun_send_id")
	private String mailgunSendId;
	
	@Field("recipient_email")
	private String recipientEmail;
	
	@Field("send_date")
	private Date sendDate;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getOutboundeMailId() {
		return outboundeMailId;
	}

	public void setOutboundeMailId(String outboundeMailId) {
		this.outboundeMailId = outboundeMailId;
	}

	public String getMailgunMessage() {
		return mailgunMessage;
	}

	public void setMailgunMessage(String mailgunMessage) {
		this.mailgunMessage = mailgunMessage;
	}

	public String getMailgunSendId() {
		return mailgunSendId;
	}

	public void setMailgunSendId(String mailgunSendId) {
		this.mailgunSendId = mailgunSendId;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
	public String getRecipientEmail()
	{
		return recipientEmail;
	}
	
	public void setRecipientEmail(String recipientEmail)
	{
		this.recipientEmail = recipientEmail;
	}
}
