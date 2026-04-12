package com.jiubuntu.settlement.order.domain;

import com.jiubuntu.settlement.common.domain.BaseEntity;
import com.jiubuntu.settlement.common.exception.CommonCode;
import com.jiubuntu.settlement.common.exception.CommonException;
import com.jiubuntu.settlement.coupon.domain.MemberCoupon;
import com.jiubuntu.settlement.member.domain.Member;
import com.jiubuntu.settlement.product.domain.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", nullable = false)
    private Member buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_coupon_id")
    private MemberCoupon memberCoupon;

    @Column(nullable = false)
    private String shippingAddress;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private BigDecimal originalAmount;

    @Column(nullable = false)
    private BigDecimal discountAmount;

    @Column(nullable = false)
    private BigDecimal finalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    private Order(Member buyer, Product product, MemberCoupon memberCoupon,
                  String shippingAddress, int quantity, BigDecimal originalAmount, BigDecimal discountAmount, BigDecimal finalAmount) {
        this.buyer = buyer;
        this.product = product;
        this.memberCoupon = memberCoupon;
        this.shippingAddress = shippingAddress;
        this.quantity = quantity;
        this.originalAmount = originalAmount;
        this.discountAmount = discountAmount;
        this.finalAmount = finalAmount;
        this.status = OrderStatus.PENDING;
    }

    public static Order create(Member buyer, Product product, MemberCoupon memberCoupon,
                               String shippingAddress, int quantity, BigDecimal originalAmount, BigDecimal discountAmount, BigDecimal finalAmount) {
        return new Order(buyer, product, memberCoupon, shippingAddress, quantity, originalAmount, discountAmount, finalAmount);
    }

    public void pay() {
        if (this.status != OrderStatus.PENDING) {
            throw new CommonException(CommonCode.ORDER_ALREADY_PAID);
        }
        this.status = OrderStatus.PAID;
    }

    public void startShipping() {
        this.status = OrderStatus.SHIPPING;
    }

    public void deliver() {
        this.status = OrderStatus.DELIVERED;
    }

    public void confirm() {
        this.status = OrderStatus.CONFIRMED;
    }

    public void cancel() {
        if (this.status != OrderStatus.PENDING && this.status != OrderStatus.PAID) {
            throw new CommonException(CommonCode.ORDER_CANNOT_CANCEL);
        }
        this.status = OrderStatus.CANCELLED;
    }

    public boolean isCancellable() {
        return this.status == OrderStatus.PENDING || this.status == OrderStatus.PAID;
    }
}
