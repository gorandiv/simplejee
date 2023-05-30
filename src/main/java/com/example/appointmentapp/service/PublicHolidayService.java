package com.example.appointmentapp.service;

import com.example.appointmentapp.dto.PublicHoliday;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Singleton;
import jakarta.ws.rs.HttpMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;

/**
 * @author goran.divovic
 */
@Singleton
public class PublicHolidayService {

    private static final String EXTERNAL_API_URL = "https://date.nager.at/api/v2/publicholidays/2023/RS";

    /**
     * Finds all {@link PublicHoliday}s in Serbia for 2023.
     *
     * @return List of {@link PublicHoliday}s
     * */
    public List<PublicHoliday> getPublicHolidays() throws IOException {
        URL obj = new URL(EXTERNAL_API_URL);

        HttpURLConnection urlConnection = (HttpURLConnection) obj.openConnection();
        urlConnection.setRequestMethod(HttpMethod.GET);

        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        String jsonResponse = response.toString();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonResponse, new TypeReference<>(){});
    }

    /**
     * Finds {@link PublicHoliday} for given date.
     *
     * @param date filter criteria
     *
     * @return {@link PublicHoliday} object if exists, otherwise returns {@link Optional#empty()}
     * */
    public Optional<PublicHoliday> getPublicHolidaysForDate(String date) {
        try {
            return Optional.of(
                    getPublicHolidays().stream().filter(ph -> ph.getDate().equals(date)).findFirst().get());
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
