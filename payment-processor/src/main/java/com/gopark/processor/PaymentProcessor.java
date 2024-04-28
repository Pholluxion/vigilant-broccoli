package com.gopark.processor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Random;

import com.gopark.model.Payment;
import jakarta.enterprise.context.ApplicationScoped;


import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.reactive.messaging.annotations.Blocking;

@ApplicationScoped
public class PaymentProcessor {

    private final Random random = new Random();

    @Incoming("requests")
    @Outgoing("payments")
    @Blocking
    public Payment process(String paymentRequest) throws InterruptedException {
        Thread.sleep(1000);
        Payment payment = new Payment();
        payment.setPaymentId(Integer.valueOf(paymentRequest));
        payment.setPaymentTime(OffsetDateTime.now());
        payment.setSpot(random.nextInt(9999999));
        payment.setFee(random.nextInt(9999999));
        payment.setPaidAmount(BigDecimal.valueOf(random.nextInt(9999999)));

        return payment;
    }
}

