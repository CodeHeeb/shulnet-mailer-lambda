package com.shulnet.mailer;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.shulnet.mailer.models.MailerLog;

public interface MailerLogRepository extends MongoRepository<MailerLog, String>{

}
