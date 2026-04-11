package com.jiubuntu.settlement.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberStatus {
    ACTIVE("정상"),
    INACTIVE("비활성"),
    BANNED("정지");

    private final String description;
}
