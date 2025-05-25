package Model.BO;

import Model.BEAN.User;
import Model.DAO.UserDAO;

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

    public User getUserById(int id) {
        return UserDAO.findById(id);
    }

    public boolean updatePassword(int userId, String newPassword) {
        return UserDAO.updatePassword(userId, newPassword);
    }

    public boolean updateFullname(int userId, String newFullname) {
        return UserDAO.updateFullname(userId, newFullname);
    }
}
