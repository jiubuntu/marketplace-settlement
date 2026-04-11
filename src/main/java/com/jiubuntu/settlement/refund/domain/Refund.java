package com.jiubuntu.settlement.refund.domain;

import com.jiubuntu.settlement.common.domain.BaseEntity;
import com.jiubuntu.settlement.common.exception.CommonCode;
import com.jiubuntu.settlement.common.exception.CommonException;
import com.jiubuntu.settlement.order.domain.Order;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "refunds")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Refund extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false)
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RefundStatus status;

    @Column(nullable = false)
    private BigDecimal refundAmount;

    private LocalDateTime processedAt;

    private Refund(Order order, String reason, BigDecimal refundAmount) {
        this.order = order;
        this.reason = reason;
        this.refundAmount = refundAmount;
        this.status = RefundStatus.REQUESTED;
    }

    public static Refund create(Order order, String reason, BigDecimal refundAmount) {
        return new Refund(order, reason, refundAmount);
    }

    public void approve() {
        if (this.status != RefundStatus.REQUESTED) {
            throw new CommonException(CommonCode.REFUND_ALREADY_PROCESSED);
        }
        this.status = RefundStatus.APPROVED;
        this.processedAt = LocalDateTime.now();
    }

    public void reject() {
        if (this.status != RefundStatus.REQUESTED) {
            throw new CommonException(CommonCode.REFUND_ALREADY_PROCESSED);
        }
        this.status = RefundStatus.REJECTED;
        this.processedAt = LocalDateTime.now();
    }

    public boolean isPending() {
        return this.status == RefundStatus.REQUESTED;
    }
}
