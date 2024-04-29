package com.gopark.processor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Random;

import com.gopark.model.Payment;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;


import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.reactive.messaging.annotations.Blocking;

@ApplicationScoped
public class PaymentProcessor {


    @Incoming("requests")
    @Outgoing("payments")
    @Blocking
    public Payment process(JsonObject paymentRequest) throws InterruptedException {

        Thread.sleep(1000);

        Payment payment = new Payment();
        payment.setPaymentId(paymentRequest.getInteger("paymentId"));
        payment.setPaymentTime(OffsetDateTime.now());
        payment.setSpot(paymentRequest.getInteger("spot"));
        payment.setFee(paymentRequest.getInteger("fee"));
        payment.setPaidAmount(BigDecimal.valueOf(paymentRequest.getDouble("paidAmount")));

        savePayment(payment);

        return payment;
    }

    @Transactional
    public void savePayment(Payment payment) {
        payment.persist();

    }
}

