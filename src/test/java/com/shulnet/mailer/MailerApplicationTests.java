package com.shulnet.mailer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.shulnet.mailer.models.OutboundEmails;
import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.message.Message;
import com.mailgun.model.message.MessageResponse;
import com.shulnet.mailer.models.FamilyMember;
import com.shulnet.mailer.models.Member;
import com.shulnet.mailer.models.Portal;
import com.shulnet.mailer.models.MailerLog;

@SpringBootTest
class MailerApplicationTests {

	@Autowired
	OutboundEmailsRepository obRepo;
	@Autowired
	MemberRepository memberRepo;
	@Autowired
	FamilyMemberRepository fmRepo;
	@Autowired
	PortalRepository portalRepo;
	@Autowired
	MailerLogRepository mailerLogRepo;
	
	@Value("${test.mailgun.apiKey}")
	private String mailGunApiKey;
	
	@Value("${test.mailgun.domain}")
	private String mailGunDomain;
	
	@Value("${test.variable.emailid}")
	private String emailId;
	
	@Test
	void contextLoads() {
		OutboundEmails obEmails = obRepo.findById(emailId).get();
		//System.out.print(obEmails);
		//System.out.print(obEmails.getPortal_id());
		List<Member> members = memberRepo.findByPortalIdAndEmailOptoutDateIsNull(obEmails.getPortal_id());
		//System.out.print(members.toString());
		List<String> emails = new ArrayList<String>();
		for (Member member : members)
		{
			System.out.println(member.getEmail());
			emails.add(member.getEmail());
		}
		
		List<Member> allMembers = memberRepo.findByPortalId(obEmails.getPortal_id());
		
		System.out.println("Test Family Member emails:");
		for (Member member : allMembers)
		{
			List<FamilyMember> familyMembers = fmRepo.findByMemberIdAndEmailOptoutDateIsNull(member.get_id());
			for (FamilyMember fm : familyMembers)
			{
				System.out.println(fm.getEmail());
				emails.add(fm.getEmail());
			}
		}
		
		//only send if we have an address to send to
		if (!emails.isEmpty())
		{
			MailgunMessagesApi mailgunMessagesApi = MailgunClient.config(mailGunApiKey)
	                .createApi(MailgunMessagesApi.class);
			
			for (String email : emails)
			{
				Message message = Message.builder()
						.from("emailer@mg.shulnet.com")
						.to(email)
						.subject(obEmails.getEmail_subject())
						.html(obEmails.getEmail_html_body())
						//.text(obEmails.getEmail_text_body())
						.build();
				
				MessageResponse messageResponse = mailgunMessagesApi.sendMessage(mailGunDomain, message);
				MailerLog newLog = new MailerLog();
				newLog.setMailgunMessage(messageResponse.getMessage());
				newLog.setMailgunSendId(messageResponse.getId());
				newLog.setOutboundeMailId(emailId);
				newLog.setRecipientEmail(email);
				newLog.setSendDate(new Date());
				mailerLogRepo.save(newLog);
				System.out.println(messageResponse.getMessage());
			}
		}
		
		System.out.println("Test portal settings:");
		Portal portalDetails = portalRepo.findById(obEmails.getPortal_id()).get();
		System.out.print(portalDetails.getPortalSettings().toString());
	}

}
