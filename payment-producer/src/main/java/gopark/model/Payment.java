package gopark.model;


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
}