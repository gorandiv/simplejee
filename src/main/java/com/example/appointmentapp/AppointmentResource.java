package com.example.appointmentapp;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/appointment")
public class AppointmentResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
}