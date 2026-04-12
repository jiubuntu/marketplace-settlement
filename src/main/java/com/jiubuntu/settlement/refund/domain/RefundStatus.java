package com.jiubuntu.settlement.refund.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RefundStatus {
    REQUESTED("환불 요청"),
    APPROVED("환불 승인"),
    REJECTED("환불 거절");

    private final String description;
}
