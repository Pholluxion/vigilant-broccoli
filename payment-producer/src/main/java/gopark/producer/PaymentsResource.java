package gopark.producer;


import gopark.model.Payment;
import io.smallrye.mutiny.Multi;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;


@Path("/payments")
public class PaymentsResource {


    @Channel("payment-requests") Emitter<Payment> paymentRequestEmitter;

    @Broadcast
    @Channel("payments") Multi<Payment> payments;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<Payment> stream() {
        return payments;
    }
    @POST
    @Path("/request")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createRequest(Payment payment) {
        paymentRequestEmitter.send(payment);
        return Response.accepted().build();
    }


}
