package com.jiubuntu.settlement.product.domain;

import com.jiubuntu.settlement.common.domain.BaseEntity;
import com.jiubuntu.settlement.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private Member seller;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductStatus status;

    private Product(Member seller, String name, String description, BigDecimal price) {
        this.seller = seller;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = ProductStatus.ON_SALE;
    }

    public static Product create(Member seller, String name, String description, BigDecimal price) {
        return new Product(seller, name, description, price);
    }

    public boolean isOnSale() {
        return this.status == ProductStatus.ON_SALE;
    }

    public void soldOut() {
        this.status = ProductStatus.SOLD_OUT;
    }

    public void discontinue() {
        this.status = ProductStatus.DISCONTINUED;
    }

    public void reopen() {
        this.status = ProductStatus.ON_SALE;
    }
}
