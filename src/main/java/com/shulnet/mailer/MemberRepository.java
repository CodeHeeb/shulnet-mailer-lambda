package com.shulnet.mailer;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shulnet.mailer.models.Member;

public interface MemberRepository extends MongoRepository<Member, String> {

	List<Member> findByPortalId(String portal_id);
}
