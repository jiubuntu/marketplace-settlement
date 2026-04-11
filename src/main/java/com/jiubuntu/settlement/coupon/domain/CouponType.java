package com.jiubuntu.settlement.coupon.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CouponType {
    SELLER("판매자 쿠폰"),
    PLATFORM("플랫폼 쿠폰");

    private final String description;
}
