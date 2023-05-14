package com.example.appointmentapp.service;

import com.example.appointmentapp.dto.AppointmentDto;
import com.example.appointmentapp.model.Appointment;
import com.example.appointmentapp.dto.PublicHoliday;
import com.example.appointmentapp.utility.AppointmentDocumentMapper;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author goran.divovic
 */
@Singleton
public class AppointmentService {

    @Inject
    PublicHolidayService publicHolidayService;

    private static MongoCollection<Document> collection;
    private static final String DB_NAME = "appointmentdb";
    private static final String DB_COLLECTION_NAME = "appointment";

    @PostConstruct
    public void init() {
        //todo: @goran.divovic change locahost to mongo to provide setup for docker
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase(DB_NAME);
        collection = database.getCollection(DB_COLLECTION_NAME);
        createAppointmentInDatabase();
    }

    /**
     * Find ({@link Appointment} by given id.
     *
     * @param id String Identifier of Appointment.
     *
     * @return {@link Optional} value of found {@link Document}.
     * */
    public Optional<Document> findById(String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            return Optional.ofNullable(collection.find(Filters.eq("_id", objectId)).first());
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public List<AppointmentDto> findAppointmentForPublicHolidayByDate(String inputDate) {

        List<AppointmentDto> appointmentDtoList = new ArrayList<>();

        Optional<PublicHoliday> publicHoliday = publicHolidayService.getPublicHolidaysForDate(inputDate);
        if(publicHoliday.isPresent()) {
            List<Appointment> appointmentList = new ArrayList<>();
            for (Document document : collection.find(Filters.eq("date", inputDate))) {
                appointmentList.add(AppointmentDocumentMapper.toAppointment(document));
            }

            for (Appointment app : appointmentList) {
                AppointmentDto dto = new AppointmentDto(publicHoliday.get().getName(), app.getTitle(), app.getDate(), app.getDetail());
                appointmentDtoList.add(dto);
            }
        }

        return appointmentDtoList;
    }

    /**
     * Find all appointments {@link Appointment} in database.
     *
     * @return list of appointments.
     * */
    public List<Appointment> findALl() {
        List<Appointment> appointmentList = new ArrayList<>();
        for (Document document : collection.find()) {
            appointmentList.add(AppointmentDocumentMapper.toAppointment(document));
        }
        return appointmentList;
    }

    /**
     * Creates new {@link Appointment} in database.
     *
     * @param appointment Appointment object that will be saved in database.
     * */
    public void save(Appointment appointment) {
        collection.insertOne(AppointmentDocumentMapper.toDocument(appointment));
    }

    /**
     * Updates {@link Document}, replacing old values with passed new one.
     *
     * @param existingDocument Existing document that will be updated.
     * @param updatedDocument Document that contains updated values.
     * */
    public void update(Document existingDocument, Document updatedDocument) {
        collection.replaceOne(existingDocument, updatedDocument);
    }

    /**
     * Deletes one {@link Document} from database.
     *
     * @param document Document that will be deleted from database.
     * */
    public void delete(Document document) {
        collection.deleteOne(document);
    }

    private void createAppointmentInDatabase() {
        Document document = new Document();
        document.put("title", "title1");
        document.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        document.put("location", "location1");
        document.put("detail", "detail1");
        collection.insertOne(document);
    }
}
