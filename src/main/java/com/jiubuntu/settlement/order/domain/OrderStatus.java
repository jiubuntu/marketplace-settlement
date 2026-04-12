package com.jiubuntu.settlement.order.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    PENDING("결제 대기"),
    PAID("결제 완료"),
    SHIPPING("배송 중"),
    DELIVERED("배송 완료"),
    CONFIRMED("구매 확정"),
    CANCELLED("취소");

    private final String description;
}
