package com.jiubuntu.settlement.settlement.domain;

import com.jiubuntu.settlement.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "platform_commissions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlatformCommission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal rate;

    @Column(nullable = false)
    private LocalDate appliedFrom;

    private PlatformCommission(BigDecimal rate, LocalDate appliedFrom) {
        this.rate = rate;
        this.appliedFrom = appliedFrom;
    }

    public static PlatformCommission create(BigDecimal rate, LocalDate appliedFrom) {
        return new PlatformCommission(rate, appliedFrom);
    }
}
