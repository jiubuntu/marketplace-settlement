package com.jiubuntu.settlement.settlement.domain;

import com.jiubuntu.settlement.common.domain.BaseEntity;
import com.jiubuntu.settlement.common.exception.CommonCode;
import com.jiubuntu.settlement.common.exception.CommonException;
import com.jiubuntu.settlement.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "settlements")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Settlement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private Member seller;

    @Column(nullable = false)
    private LocalDate periodStart;

    @Column(nullable = false)
    private LocalDate periodEnd;

    @Column(nullable = false)
    private BigDecimal totalSalesAmount;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal commissionRate;

    @Column(nullable = false)
    private BigDecimal commissionAmount;

    @Column(nullable = false)
    private BigDecimal settlementAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SettlementStatus status;

    private LocalDateTime settledAt;

    private Settlement(Member seller, LocalDate periodStart, LocalDate periodEnd,
                       BigDecimal totalSalesAmount, BigDecimal commissionRate, BigDecimal commissionAmount, BigDecimal settlementAmount) {
        this.seller = seller;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.totalSalesAmount = totalSalesAmount;
        this.commissionRate = commissionRate;
        this.commissionAmount = commissionAmount;
        this.settlementAmount = settlementAmount;
        this.status = SettlementStatus.PENDING;
    }

    public static Settlement create(Member seller, LocalDate periodStart, LocalDate periodEnd,
                                    BigDecimal totalSalesAmount, BigDecimal commissionRate, BigDecimal commissionAmount, BigDecimal settlementAmount) {
        return new Settlement(seller, periodStart, periodEnd, totalSalesAmount, commissionRate, commissionAmount, settlementAmount);
    }

    public void complete() {
        if (this.status != SettlementStatus.PENDING) {
            throw new CommonException(CommonCode.SETTLEMENT_ALREADY_COMPLETED);
        }
        this.status = SettlementStatus.COMPLETED;
        this.settledAt = LocalDateTime.now();
    }

    public void fail() {
        this.status = SettlementStatus.FAILED;
    }

    public boolean isCompleted() {
        return this.status == SettlementStatus.COMPLETED;
    }
}
