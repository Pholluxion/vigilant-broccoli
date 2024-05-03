package com.gopark.model;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;


@Entity
@Table(name = "Payments")
@RegisterForReflection
public class Payment extends PanacheEntityBase {
    public Payment() {
        /// Default constructor
    }
    @Id
    @Column(
            nullable = false,
            updatable = false
    )
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Integer id;

    @Column(precision = 12,
            scale = 2,
            name = "paid_amount"
    )
    private BigDecimal paidAmount;

    @Column(name  = "payment_time")
    private OffsetDateTime paymentTime;

    @Column(name  = "spot_id",
            nullable = false
    )
    private Integer spot;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public OffsetDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(OffsetDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Integer getSpot() {
        return spot;
    }

    public void setSpot(Integer spot) {
        this.spot = spot;
    }
}