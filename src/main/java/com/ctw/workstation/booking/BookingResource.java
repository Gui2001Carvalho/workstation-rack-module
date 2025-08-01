package com.ctw.workstation.booking;

import com.ctw.workstation.booking.entity.Booking;
import com.ctw.workstation.rack.RackRepository;
import com.ctw.workstation.rack.entity.Rack;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Path("/bookings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class BookingResource {

    @Inject
    BookingRepository repository;

    @GET
    public Response getBookings() {
        return Response.ok(repository.getBookings()).build();
    }

    @Transactional
    @POST
    public Response addBooking(Booking booking) {
        repository.addBooking(booking);
        return Response.status(Response.Status.CREATED).entity(booking).build();
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    public Response removeBooking(@PathParam("id") UUID id) {
        repository.removeBooking(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}

