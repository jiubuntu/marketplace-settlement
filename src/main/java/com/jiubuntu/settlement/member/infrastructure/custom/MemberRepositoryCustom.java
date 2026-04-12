package com.jiubuntu.settlement.member.infrastructure.custom;

import com.jiubuntu.settlement.member.domain.Member;

import java.util.Optional;

public interface MemberRepositoryCustom {

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);
}
