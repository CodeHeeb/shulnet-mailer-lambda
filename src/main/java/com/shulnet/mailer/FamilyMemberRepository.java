package com.shulnet.mailer;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.shulnet.mailer.models.FamilyMember;

public interface FamilyMemberRepository extends MongoRepository<FamilyMember, String> {

	List<FamilyMember> findByMemberId(String memberId);
	List<FamilyMember> findByMemberIdAndEmailOptoutDateIsNull(String member_id);
}
