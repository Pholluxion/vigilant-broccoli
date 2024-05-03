package com.gopark.processor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Random;

import com.gopark.model.Payment;
import com.gopark.model.PaymentStatus;
import com.gopark.model.Spot;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
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
    public Payment process(String spotId) throws InterruptedException {

        Thread.sleep(3000);

        final Integer spotIdInt = Integer.parseInt(spotId);

        Spot spot = getSpot(spotIdInt);
        Payment payment = new Payment();

        savePayment(payment,spot);

        return payment;
    }

    @Transactional
    public void savePayment(Payment payment, Spot spot) {
        payment.setPaymentTime(OffsetDateTime.now());
        payment.setSpot(spot.getId());
        payment.setPaidAmount(BigDecimal.valueOf(5.0 * random.nextInt(10)) );
        payment.persist();

        spot.setPaymentStatus(PaymentStatus.PAID);
        spot.getEntityManager().merge(spot);
    }

    @Transactional
    public Spot getSpot(Integer spotId) {
        Spot spot = new Spot();
        return spot.findById(spotId);
    }
}

