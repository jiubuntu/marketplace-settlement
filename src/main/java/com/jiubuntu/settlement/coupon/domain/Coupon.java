package com.jiubuntu.settlement.coupon.domain;

import com.jiubuntu.settlement.common.domain.BaseEntity;
import com.jiubuntu.settlement.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "coupons")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Member seller;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CouponType couponType;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DiscountType discountType;

    @Column(nullable = false)
    private BigDecimal discountValue;

    @Column(nullable = false)
    private BigDecimal minOrderAmount;

    @Column
    private BigDecimal maxDiscountAmount;

    @Column(nullable = false)
    private LocalDateTime startAt;

    @Column(nullable = false)
    private LocalDateTime endAt;

    @Column(nullable = false)
    private boolean enabled;

    private Coupon(Member seller, CouponType couponType, String name, DiscountType discountType, BigDecimal discountValue,
                   BigDecimal minOrderAmount, BigDecimal maxDiscountAmount, LocalDateTime startAt, LocalDateTime endAt) {
        this.seller = seller;
        this.couponType = couponType;
        this.name = name;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.minOrderAmount = minOrderAmount;
        this.maxDiscountAmount = maxDiscountAmount;
        this.startAt = startAt;
        this.endAt = endAt;
        this.enabled = true;
    }

    public static Coupon createSellerCoupon(Member seller, String name, DiscountType discountType, BigDecimal discountValue,
                                            BigDecimal minOrderAmount, BigDecimal maxDiscountAmount, LocalDateTime startAt, LocalDateTime endAt) {
        return new Coupon(seller, CouponType.SELLER, name, discountType, discountValue, minOrderAmount, maxDiscountAmount, startAt, endAt);
    }

    public static Coupon createPlatformCoupon(String name, DiscountType discountType, BigDecimal discountValue,
                                              BigDecimal minOrderAmount, BigDecimal maxDiscountAmount, LocalDateTime startAt, LocalDateTime endAt) {
        return new Coupon(null, CouponType.PLATFORM, name, discountType, discountValue, minOrderAmount, maxDiscountAmount, startAt, endAt);
    }

    public boolean isAvailable(LocalDateTime now, BigDecimal orderAmount) {
        return this.enabled && now.isAfter(startAt) && now.isBefore(endAt) && orderAmount.compareTo(minOrderAmount) >= 0;
    }

    public void disable() {
        this.enabled = false;
    }
}
