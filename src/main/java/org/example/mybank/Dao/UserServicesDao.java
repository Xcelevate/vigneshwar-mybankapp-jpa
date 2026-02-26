package org.example.mybank.Dao;

import jakarta.persistence.EntityManager;
import org.example.mybank.entity.Users;
import org.example.mybank.networks.connection;


public class UserServicesDao {

    public  Users getUserById(String userId ){
      EntityManager em = connection.entityManager();
      Users user = em.find(Users.class,userId);
      return user;
    }
}
