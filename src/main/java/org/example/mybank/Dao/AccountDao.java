package org.example.mybank.Dao;

import jakarta.persistence.EntityManager;
import org.example.mybank.entity.Account;
import org.example.mybank.networks.connection;

import java.util.List;

public class AccountDao {

    // <<<------------------ listOfAccount ------------------------>>>

    public List<Account> getListOfAcc(String userId){
        EntityManager em = connection.entityManager();
        try {
            return em.createQuery(
                    "select a from Account a where a.user.userId = :uid",
                    Account.class
            )
                    .setParameter("uid",userId)
                    .getResultList();
        }finally{
            em.close();
        }
    }

    // <<<------------------ createAccount ------------------------>>>

    public void save(Account account){
        EntityManager em = connection.entityManager();
        try{
            em.getTransaction().begin();
            em.persist(account);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            throw e;
        }finally {
            em.close();
        }
    }

    // <<<------------------ view balance ------------------------>>>

    public Account getBalance(String accNo, String userId){
        EntityManager em = connection.entityManager();
        try {
            return em.createQuery(
                            "SELECT a FROM Account a WHERE a.accountNumber = :accNo AND a.user.userId = :uid",
                            Account.class
                    )
                    .setParameter("accNo", accNo)
                    .setParameter("uid", userId)
                    .getSingleResult();
        } finally {
            em.close();

        }
    }
}

