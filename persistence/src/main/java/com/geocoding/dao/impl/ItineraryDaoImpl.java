package com.geocoding.dao.impl;

import com.geocoding.dao.ItineraryDao;
import com.geocoding.model.Itinerary;
import com.geocoding.model.Stay;
import com.geocoding.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ItineraryDaoImpl implements ItineraryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Itinerary createItinerary(Itinerary itinerary, int userId) {
        User userPersistence = entityManager.find(User.class, userId);
        if (userPersistence != null) {
            itinerary.addUser(userPersistence);
            itinerary.setState("created");
            userPersistence.setItinerary(itinerary);
            entityManager.persist(itinerary);
            return itinerary;
        }
        return null;
    }

    @Override
    public Itinerary getItinerary(int id) {
        return entityManager.find(Itinerary.class, id);
    }

    @Override
    public List<Itinerary> getItineraries() {
        String hql = "SELECT i FROM Itinerary i";
        TypedQuery<Itinerary> query = entityManager.createQuery(hql, Itinerary.class);
        return query.getResultList();
    }

    @Override
    public Itinerary updateItinerary(Itinerary itinerary, int userId, int stayId) {
        Itinerary itineraryPersistence = entityManager.find(Itinerary.class, itinerary.getId());
        User userPersistence = entityManager.find(User.class, userId);
        Stay stayPersistence = entityManager.find(Stay.class, stayId);
        if(itineraryPersistence != null && userPersistence != null && stayPersistence != null) {
            itineraryPersistence.setDescription(itinerary.getDescription());
            itineraryPersistence.setStartDate(itinerary.getStartDate());
            itineraryPersistence.setEndDate(itinerary.getEndDate());
            // Controlla se l'itinerario non ha l'utente id specificato
            if(!itineraryPersistence.getUsers().contains(userPersistence)) {
                itineraryPersistence.getUsers().add(userPersistence);
            }
            // Controlla se l'itinerario non ha lo stay id specificato
            if(!itineraryPersistence.getStays().contains(stayPersistence)) {
                itineraryPersistence.getStays().add(stayPersistence);
                itineraryPersistence.setState("ready");
            }
            entityManager.merge(itineraryPersistence);
            return itineraryPersistence;
        }
        return null;
    }

    @Override
    public String deleteItinerary(Itinerary itinerary) {
        Itinerary itineraryPersistence = entityManager.find(Itinerary.class, itinerary.getId());
        if(itineraryPersistence != null) {
            itineraryPersistence.setDeleted(true);
            return "Itinerary deleted";
        }
        return "This itinerary does not exist";
    }
}
