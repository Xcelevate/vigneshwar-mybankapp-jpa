package org.example.mybank.services;

import jakarta.persistence.EntityManager;
import org.example.mybank.Dao.UserServicesDao;
import org.example.mybank.entity.Users;
import org.example.mybank.networks.connection;

import java.sql.Connection;

public class LoginServices {
    public static Users currentUser = null;
    UserServicesDao userServicesDao = new UserServicesDao();

    public boolean isLoggedIn() {
        return currentUser!=null;
    }

    public boolean login(String userId, String password) {
        Users user =  userServicesDao.getUserById(userId);
        if(user!=null && user.getPassword().equals(password)){
            currentUser = user;
            return true;
        }
        return false;
    }
    public Users getCurrentUser() {
        return currentUser;
    }
}
