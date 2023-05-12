package com.example.appointmentapp.dao;

import com.example.appointmentapp.model.Appointment;
import com.example.appointmentapp.utility.AppointmentDocumentMapper;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author goran.divovic
 */
@Singleton
public class AppointmentDao {

    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> collection;

    @PostConstruct
    public void init() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("appointmentdb");
        collection = database.getCollection("appointment");
    }

    public Optional<Document> findById(String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            return Optional.ofNullable(collection.find(Filters.eq("_id", objectId)).first());
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public List<Appointment> findALl() {
        List<Appointment> appointmentList = new ArrayList<>();
        for (Document document : collection.find()) {
            appointmentList.add(AppointmentDocumentMapper.toAppointment(document));
        }
        return appointmentList;
    }

    public void save(Appointment appointment) {
        collection.insertOne(AppointmentDocumentMapper.toDocument(appointment));
    }

    public void update(Document existingDocument, Document updatedDocument) {
        collection.replaceOne(existingDocument, updatedDocument);
    }

    public void delete(Document document) {
        collection.deleteOne(document);
    }
}
