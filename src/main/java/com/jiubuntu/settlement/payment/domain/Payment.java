package com.jiubuntu.settlement.payment.domain;

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
@Table(name = "payments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Order order;

    @Column(nullable = false)
    private BigDecimal amount;

    private String pgTransactionId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    private LocalDateTime paidAt;

    private Payment(Order order, BigDecimal amount) {
        this.order = order;
        this.amount = amount;
        this.status = PaymentStatus.PENDING;
    }

    public static Payment create(Order order, BigDecimal amount) {
        return new Payment(order, amount);
    }

    public void complete(String pgTransactionId) {
        if (this.status != PaymentStatus.PENDING) {
            throw new CommonException(CommonCode.PAYMENT_ALREADY_COMPLETED);
        }
        this.pgTransactionId = pgTransactionId;
        this.status = PaymentStatus.SUCCESS;
        this.paidAt = LocalDateTime.now();
    }

    public void fail() {
        this.status = PaymentStatus.FAILED;
    }

    public void cancel() {
        this.status = PaymentStatus.CANCELLED;
    }

    public boolean isSuccess() {
        return this.status == PaymentStatus.SUCCESS;
    }
}
