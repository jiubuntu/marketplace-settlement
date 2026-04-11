package com.jiubuntu.settlement.coupon.domain;

import com.jiubuntu.settlement.common.domain.BaseEntity;
import com.jiubuntu.settlement.common.exception.CommonCode;
import com.jiubuntu.settlement.common.exception.CommonException;
import com.jiubuntu.settlement.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "member_coupons")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberCoupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id", nullable = false)
    private Coupon coupon;

    @Column(nullable = false)
    private boolean used;

    private LocalDateTime usedAt;

    private MemberCoupon(Member member, Coupon coupon) {
        this.member = member;
        this.coupon = coupon;
        this.used = false;
    }

    public static MemberCoupon issue(Member member, Coupon coupon) {
        return new MemberCoupon(member, coupon);
    }

    public void use() {
        if (this.used) {
            throw new CommonException(CommonCode.COUPON_ALREADY_USED);
        }
        this.used = true;
        this.usedAt = LocalDateTime.now();
    }

    public boolean isUsable() {
        return !this.used;
    }
}
