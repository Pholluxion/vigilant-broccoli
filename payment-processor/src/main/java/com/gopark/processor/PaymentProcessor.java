package com.gopark.processor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Random;

import com.gopark.model.Payment;
import com.gopark.model.PaymentStatus;
import com.gopark.model.Spot;
import jakarta.enterprise.context.ApplicationScoped;


import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.reactive.messaging.annotations.Blocking;

@ApplicationScoped
public class PaymentProcessor {


    private static final Random random = new Random();


    @Incoming("requests")
    @Outgoing("payments")
    @Blocking
    public String process(String spotId) throws InterruptedException {

        Thread.sleep(3000);

        final Integer spotIdInt = Integer.parseInt(spotId);

        Spot spot = getSpot(spotIdInt);
        Payment payment = new Payment();

        if (spot.getPaymentStatus() == PaymentStatus.PAID) {
            return "Payment already done for spot " + spotId;
        }

       final Integer id = savePayment(payment,spot);

        return id.toString();
    }

    @Transactional
    public Integer savePayment(Payment payment, Spot spot) {

        payment.setPaymentTime(OffsetDateTime.now());
        payment.setSpot(spot.getId());
        payment.setPaidAmount(BigDecimal.valueOf(5.0 * random.nextInt(10)) );
        payment.persist();

        spot.setPaymentStatus(PaymentStatus.PAID);
        spot.getEntityManager().merge(spot);

        return payment.getId();
    }

    @Transactional
    public Spot getSpot(Integer spotId) {
        Spot spot = new Spot();
        return spot.findById(spotId);
    }
}

