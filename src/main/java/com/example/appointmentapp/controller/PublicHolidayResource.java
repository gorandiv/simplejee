
package com.example.appointmentapp.controller;

import com.example.appointmentapp.model.PublicHoliday;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HttpMethod;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


/**
 * @author goran.divovic
 */
@Path("/public-holidays")
public class PublicHolidayResource {

    private static final String EXTERNAL_API_URL = "https://date.nager.at/api/v2/publicholidays/2020/RS";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPublicHolidaysInSerbia() throws IOException {
        URL obj = new URL(EXTERNAL_API_URL);

        HttpURLConnection urlConnection = (HttpURLConnection) obj.openConnection();
        urlConnection.setRequestMethod(HttpMethod.GET);

        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        String jsonResponse = response.toString();
        ObjectMapper mapper = new ObjectMapper();
        List<PublicHoliday> holidays = mapper.readValue(jsonResponse, new TypeReference<>() {
        });

        return Response.ok(holidays).build();
    }
}

