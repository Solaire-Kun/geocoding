package com.geocoding.dao.impl;

import com.geocoding.dao.StayDao;
import com.geocoding.model.City;
import com.geocoding.model.Itinerary;
import com.geocoding.model.Stay;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class StayDaoImpl implements StayDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Stay createStay(Stay stay, int itineraryId) {
        Itinerary itineraryPersistence = entityManager.find(Itinerary.class, itineraryId);
        if(itineraryPersistence != null) {
            stay.setItinerary(itineraryPersistence);
            entityManager.persist(stay);
            return stay;
        }
        return null;
    }

    @Override
    public Stay getStay(int id) {
        return entityManager.find(Stay.class, id);
    }

    @Override
    public List<Stay> getStays() {
        String hql = "SELECT s FROM Stay s";
        TypedQuery<Stay> query = entityManager.createQuery(hql, Stay.class);
        return query.getResultList();
    }

    @Override
    public Stay updateStay(Stay stay) {
        Stay stayPersistence = entityManager.find(Stay.class, stay.getId());
        if(stayPersistence != null) {
            stayPersistence.setDescription(stay.getDescription());
            stayPersistence.setStayDate(stay.getStayDate());
            entityManager.merge(stayPersistence);
            return stayPersistence;
        }
        return null;
    }

    @Override
    public String deleteStay(Stay itinerary) {
        Stay stayPersistence = entityManager.find(Stay.class, itinerary.getId());
        if(stayPersistence != null) {
            stayPersistence.setDeleted(true);
            return "Stay deleted";
        }
        return "This stay does not exist";
    }
}
