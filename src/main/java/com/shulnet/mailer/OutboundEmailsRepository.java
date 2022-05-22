package com.shulnet.mailer;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shulnet.mailer.models.OutboundEmails;

public interface OutboundEmailsRepository extends MongoRepository<OutboundEmails, String> {

}
