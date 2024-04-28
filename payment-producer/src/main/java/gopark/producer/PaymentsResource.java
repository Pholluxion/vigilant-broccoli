package gopark.producer;


import gopark.model.Payment;
import io.smallrye.mutiny.Multi;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.Random;

@Path("/payments")
public class PaymentsResource {

    private final Random random = new Random();
    @Channel("payment-requests") Emitter<Integer> paymentRequestEmitter;

    @Channel("payments") Multi<Payment> payments;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<Payment> stream() {
        return payments;
    }
    @POST
    @Path("/request")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer createRequest(Payment payment) {
        int rValue = random.nextInt(9999999);
        paymentRequestEmitter.send(rValue);
        return rValue;
    }


}
