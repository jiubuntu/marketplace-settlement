package com.jiubuntu.settlement.product.domain;

import com.jiubuntu.settlement.common.exception.CommonCode;
import com.jiubuntu.settlement.common.exception.CommonException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "product_stocks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, unique = true)
    private Product product;

    @Column(nullable = false)
    private int stock;

    @Version
    private int version;

    private ProductStock(Product product, int stock) {
        this.product = product;
        this.stock = stock;
    }

    public static ProductStock create(Product product, int initialStock) {
        return new ProductStock(product, initialStock);
    }

    public void decrease(int quantity) {
        if (this.stock < quantity) {
            throw new CommonException(CommonCode.PRODUCT_OUT_OF_STOCK);
        }
        this.stock -= quantity;
    }

    public void increase(int quantity) {
        this.stock += quantity;
    }

    public boolean isSufficient(int quantity) {
        return this.stock >= quantity;
    }
}
