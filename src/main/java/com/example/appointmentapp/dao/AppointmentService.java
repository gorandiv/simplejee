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
public class AppointmentService {

    private static MongoCollection<Document> collection;
    private static final String DB_NAME = "appointmentdb";
    private static final String DB_COLLECTION_NAME = "appointment";

    @PostConstruct
    public void init() {
        //todo: @goran.divovic change locahost to mongo to provide setup for docker
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase(DB_NAME);
        collection = database.getCollection(DB_COLLECTION_NAME);
    }

    /**
     * Find ({@link Appointment} by given id.
     *
     * @param id String Identifier of Appointment
     *
     * @return {@link Optional} value of found {@link Document}
     * */
    public Optional<Document> findById(String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            return Optional.ofNullable(collection.find(Filters.eq("_id", objectId)).first());
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    /**
     * Find all appointments {@link Appointment} in database.
     *
     * @return list of appointments
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
}
