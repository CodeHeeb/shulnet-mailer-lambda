package com.shulnet.mailer;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shulnet.mailer.models.OutboundEmails;
import com.shulnet.mailer.models.Member;

@SpringBootTest
class MailerApplicationTests {

	@Autowired
	OutboundEmailsRepository obRepo;
	@Autowired
	MemberRepository memberRepo;
	
	@Test
	void contextLoads() {
		OutboundEmails obEmails = obRepo.findById("6287f7940cd7dd5a913f8a7e").get();
		//System.out.print(obEmails);
		System.out.print(obEmails.getPortal_id());
		List<Member> members = memberRepo.findByPortalId(obEmails.getPortal_id());
		System.out.print(members.toString());
	}

}
