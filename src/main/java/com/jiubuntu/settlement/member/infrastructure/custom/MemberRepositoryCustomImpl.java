package com.jiubuntu.settlement.member.infrastructure.custom;

import com.jiubuntu.settlement.member.domain.Member;
import com.jiubuntu.settlement.member.domain.QMember;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QMember member = QMember.member;

    @Override
    public Optional<Member> findByEmail(String email) {
        return Optional.ofNullable(
                queryFactory.selectFrom(member)
                        .where(member.email.eq(email).and(isActive()))
                        .fetchOne()
        );
    }

    @Override
    public boolean existsByEmail(String email) {
        return queryFactory.selectOne()
                .from(member)
                .where(member.email.eq(email).and(isActive()))
                .fetchFirst() != null;
    }

    private BooleanExpression isActive() {
        return member.active.isTrue();
    }
}
