package com.shulnet.mailer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.shulnet.mailer.models.OutboundEmails;


@SpringBootApplication
public class MailerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailerApplication.class, args);
	}
	
	@Autowired
	OutboundEmailsRepository oeRepo;
	
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
						//6287eeb80cd7dd5a913f8a55 = 24
						String outboundEmailId = detailString.substring(startIndex, startIndex+24);
						System.out.println(outboundEmailId);
						OutboundEmails emailData = oeRepo.findById(outboundEmailId).get();
						System.out.println(emailData.toString());
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
