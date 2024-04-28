package com.gopark.model;


import io.quarkus.runtime.annotations.RegisterForReflection;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@RegisterForReflection
public class Payment {

    private Integer paymentId;

    private BigDecimal paidAmount;

    private OffsetDateTime paymentTime;

    private Integer spot;

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