package com.example.appointmentapp.controller;

import com.example.appointmentapp.dao.AppointmentService;
import com.example.appointmentapp.model.Appointment;
import com.example.appointmentapp.utility.AppointmentDocumentMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.Document;

import java.util.List;
import java.util.Optional;

/**
 * @author goran.divovic
 */
@Path("/v1/appointment")
public class AppointmentResource {

    @Inject
    AppointmentService appointmentService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") String id) {
        Optional<Document> appointment = appointmentService.findById(id);
        if (appointment.isPresent()) {
            return Response.ok(AppointmentDocumentMapper.toAppointment(appointment.get())).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Appointment> appointments = appointmentService.findALl();
        if (!appointments.isEmpty()) {
            return Response.ok(appointments).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Appointment appointment) {
        if (appointment.getTitle() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Missing mandatory attribute - title").build();
        }
        appointmentService.save(appointment);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") String id, Appointment appointment) {
        if (appointment.getTitle() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Missing mandatory attribute - title").build();
        }
        Optional<Document> existingDocument = appointmentService.findById(id);
        if (existingDocument.isPresent()) {
            appointmentService.update(existingDocument.get(), AppointmentDocumentMapper.toDocument(appointment));
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        Optional<Document> existingDocument = appointmentService.findById(id);
        if (existingDocument.isPresent()) {
            appointmentService.delete(existingDocument.get());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
