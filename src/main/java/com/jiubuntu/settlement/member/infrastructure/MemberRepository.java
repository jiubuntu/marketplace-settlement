package com.jiubuntu.settlement.member.infrastructure;

import com.jiubuntu.settlement.member.domain.Member;
import com.jiubuntu.settlement.member.infrastructure.custom.MemberRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
}
