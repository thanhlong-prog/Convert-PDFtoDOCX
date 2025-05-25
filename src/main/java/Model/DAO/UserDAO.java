package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.BEAN.User;

public class UserDAO {
    private static final Logger logger = Logger.getLogger(ConvertJobDAO.class.getName());
    public static User findByUsername(String username) {
        try (Connection conn = DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
                        rs.getString("fullname"));
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in FindByUserName Method", e);
        }
        return null;
    }

    public static User findById(int id) {
        try (Connection conn = DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
                        rs.getString("fullname"));
            }
        } catch (Exception e) {
           logger.log(Level.SEVERE, "Error in FindByID method", e);
        }
        return null;
    }

    public static boolean insert(User user) {
        try (Connection conn = DB.getConnection()) {
            PreparedStatement stmt = conn
                    .prepareStatement("INSERT INTO users (username, password, fullname) VALUES (?, ?, ?)");
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFullname());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in Insert method", e);
        }
        return false;
    }

    public static boolean updatePassword(int userId, String newPassword) {
        try (Connection conn = DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE users SET password = ? WHERE id = ?");
            stmt.setString(1, newPassword);
            stmt.setInt(2, userId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in updatePassword Method", e);
        }
        return false;
    }

    public static boolean updateFullname(int userId, String newFullname) {
        try (Connection conn = DB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE users SET fullname = ? WHERE id = ?");
            stmt.setString(1, newFullname);
            stmt.setInt(2, userId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
           logger.log(Level.SEVERE, "Error in updateFullname Method", e);
        }
        return false;
    }
}
