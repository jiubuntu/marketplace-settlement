package com.jiubuntu.settlement.settlement.domain;

import com.jiubuntu.settlement.common.domain.BaseEntity;
import com.jiubuntu.settlement.order.domain.Order;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "settlement_items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SettlementItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "settlement_id", nullable = false)
    private Settlement settlement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false)
    private BigDecimal commissionAmount;

    private SettlementItem(Settlement settlement, Order order, BigDecimal commissionAmount) {
        this.settlement = settlement;
        this.order = order;
        this.commissionAmount = commissionAmount;
    }

    public static SettlementItem create(Settlement settlement, Order order, BigDecimal commissionAmount) {
        return new SettlementItem(settlement, order, commissionAmount);
    }
}
