package gopark.producer;


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

    @Broadcast
    @Channel("payment-requests") Emitter<Integer> paymentRequestEmitter;

    @Channel("payments") Multi<String> payments;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<String> stream() {
        return payments;
    }


    @POST
    @Path("/request")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createRequest(Integer spotId) {
        paymentRequestEmitter.send(spotId);
        return Response.accepted().build();
    }


}
