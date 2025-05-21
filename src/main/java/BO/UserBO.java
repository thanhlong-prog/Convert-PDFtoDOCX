package BO;

import DAO.UserDAO;
import Model.User;

public class UserBO {
    public boolean register(User user) {
        if (UserDAO.findByUsername(user.getUsername()) != null) {
            return false; 
        }
        return UserDAO.insert(user);
    }

    public User login(String username, String password) {
        User user = UserDAO.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
