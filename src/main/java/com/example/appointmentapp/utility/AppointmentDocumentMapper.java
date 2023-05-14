package com.example.appointmentapp.utility;

import com.example.appointmentapp.model.Appointment;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author goran.divovic
 */
public class AppointmentDocumentMapper {

    /**
     * Map values from {@link Appointment} to {@link Document}.
     *
     * @param appointment Appointment object to be mapped into Document.
     *
     * @return an Appointment object mapped from Document.
     * */
    public static Document toDocument(Appointment appointment) {
        Document document = new Document();
        if (appointment.getId() != null) {
            document.put("_id", new ObjectId(appointment.getId()));
        }
        document.put("title", appointment.getTitle());
        document.put("date", appointment.getDate());
        document.put("location", appointment.getLocation());
        document.put("detail", appointment.getDetail());

        return document;
    }

    /**
     * Map values from {@link Document} to {@link Appointment}.
     *
     * @param document Document object to be mapped into Appointment.
     *
     * @return a Document object mapped from Appointment.
     * */
    public static Appointment toAppointment(Document document) {
        Appointment appointment = new Appointment();
        try {
            appointment.setId(document.getObjectId("_id").toString());
            appointment.setTitle(document.getString("title"));
            if(document.getString("date") != null) {
                appointment.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(document.getString("date")));
            }
            appointment.setLocation(document.getString("location"));
            appointment.setDetail(document.getString("detail"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return appointment;
    }
}
