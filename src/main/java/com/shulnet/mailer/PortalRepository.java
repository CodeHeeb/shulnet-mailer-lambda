package com.shulnet.mailer;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.shulnet.mailer.models.Portal;

public interface PortalRepository extends MongoRepository<Portal, String> {

}
