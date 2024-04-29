package com.gopark.model;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;


@Entity
@Cacheable
@Table(name = "Payments")
@RegisterForReflection
public class Payment extends PanacheEntityBase {

    @Id
    @Column(name = "payment_id")
    @SequenceGenerator(
            name = "payment_sequence",
            sequenceName = "payment_sequence",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "payment_sequence"
    )
    private Integer paymentId;

    @Column(precision = 12, scale = 2, name = "paid_amount")
    private BigDecimal paidAmount;

    @Column(name  = "payment_time")
    private OffsetDateTime paymentTime;

    @Column(name  = "spot_id")
    private Integer spot;

    @Column(name = "fee_id")
    private Integer fee;


    public Payment() {
        // TODO document why this constructor is empty
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
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

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }
}