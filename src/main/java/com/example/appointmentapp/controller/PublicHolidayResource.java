package com.example.appointmentapp.controller;

import com.example.appointmentapp.service.PublicHolidayService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;



/**
 * @author goran.divovic
 */
@Path("/v1/public-holidays")
public class PublicHolidayResource {

    @Inject
    PublicHolidayService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPublicHolidaysInSerbia() throws IOException {
        return Response.ok(service.getPublicHolidays()).build();
    }
}

