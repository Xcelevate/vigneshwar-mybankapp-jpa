package org.example.mybank.networks;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class connection {
   static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaBank");
    public static EntityManager entityManager(){
        return emf.createEntityManager();
    }
}
