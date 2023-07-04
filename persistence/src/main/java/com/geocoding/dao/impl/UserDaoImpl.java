package com.geocoding.dao.impl;

import com.geocoding.dao.UserDao;
import com.geocoding.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User createUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getUsers() {
        String hql = "SELECT u FROM User u";
        TypedQuery<User> query = entityManager.createQuery(hql, User.class);
        return query.getResultList();
    }

    @Override
    public User updateUser(User user) {
        User userPersistence = entityManager.find(User.class, user.getId());
        if(userPersistence != null) {
            userPersistence.setUsername(user.getUsername());
            userPersistence.setEmail(user.getEmail());
            userPersistence.setName(user.getName());
            userPersistence.setSurname(user.getSurname());
            entityManager.merge(userPersistence);
        }
        return userPersistence;
    }

    @Override
    public String deleteUser(User user) {
        User userPersistence = entityManager.find(User.class, user.getId());
        if(userPersistence != null) {
            userPersistence.setDeleted(true);
            return "User deleted";
        }
        return "This user does not exist";
    }
}
