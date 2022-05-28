package com.shulnet.mailer;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.message.Message;
import com.mailgun.model.message.MessageResponse;
import com.shulnet.mailer.models.FamilyMember;
import com.shulnet.mailer.models.Member;
import com.shulnet.mailer.models.OutboundEmails;
import com.shulnet.mailer.models.Portal;
import com.shulnet.mailer.models.Portal.Setting;
import com.shulnet.mailer.models.MailerLog;

@SpringBootApplication
public class MailerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailerApplication.class, args);
	}
	
	@Autowired
	OutboundEmailsRepository oeRepo;
	@Autowired
	MemberRepository memberRepo;
	@Autowired
	FamilyMemberRepository fmRepo;
	@Autowired
	PortalRepository portalRepo;
	@Autowired
	MailerLogRepository mailerLogRepo;
	
	@Bean
	public Function<Map<String, Object>, String> mailerRun() {
		return value -> {
			if (value.equals("exception")) {
				throw new RuntimeException("Intentional exception");
			}
			else {
				for (Map.Entry<String, Object> pair : value.entrySet()) {
					if (pair.getKey() == "detail")
					{
						//get our record id
						String detailString = pair.getValue().toString();
						int startIndex = detailString.indexOf("fullDocument={_id=") + 18;
						String outboundEmailId = detailString.substring(startIndex, startIndex+24);
						OutboundEmails emailData = oeRepo.findById(outboundEmailId).get();
						
						//get our emails
						List<Member> members = memberRepo.findByPortalIdAndEmailOptoutDateIsNull(emailData.getPortal_id());
						
						List<String> emails = new ArrayList<String>();
						
						for (Member member : members)
						{
							emails.add(member.getEmail());
						}
						
						//now look for family members to add who have not opted out
						List<Member> allMembers = memberRepo.findByPortalId(emailData.getPortal_id());
						
						for (Member member : allMembers)
						{
							List<FamilyMember> familyMembers = fmRepo.findByMemberIdAndEmailOptoutDateIsNull(member.get_id());
							for (FamilyMember fm : familyMembers)
							{
								emails.add(fm.getEmail());
							}
						}
						
						//get our portal settings for mailgun settings
						Portal portalDetails = portalRepo.findById(emailData.getPortal_id()).get();
						
						String mailGunApiKey = "";
						String mailGunDomain = "";
						
						for (Setting setting : portalDetails.getPortalSettings())
						{
							if (setting.optionName == "mailgun_key")
							{
								mailGunApiKey = setting.optionValue;
							}
							if (setting.optionName == "mailgun_domain")
							{
								mailGunDomain = setting.optionValue;
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
										.subject(emailData.getEmail_subject())
										.html(emailData.getEmail_html_body())
										//.text(emailData.getEmail_text_body())
										.build();
								
								MessageResponse messageResponse = mailgunMessagesApi.sendMessage(mailGunDomain, message);
								MailerLog newEmailLog = new MailerLog();
								newEmailLog.setMailgunMessage(messageResponse.getMessage());
								newEmailLog.setMailgunSendId(messageResponse.getId());
								newEmailLog.setOutboundeMailId(emailData.get_id());
								newEmailLog.setRecipientEmail(email);
								newEmailLog.setSendDate(new Date());
								mailerLogRepo.save(newEmailLog);
							}
						}
						
						Instant instant = Instant.now();
						ZoneId zoneId = ZoneId.of("America/New_York");
						ZonedDateTime zdt = instant.atZone(zoneId);
						emailData.setSend_date(java.util.Date.from(zdt.toInstant()));
						oeRepo.save(emailData);
					}
				}
				System.out.println(value);
				return value.toString();				
			}
		};
	}

}
