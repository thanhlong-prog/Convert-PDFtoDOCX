package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.BEAN.ConvertJob;

public class ConvertJobDAO {
    private static final Logger logger = Logger.getLogger(ConvertJobDAO.class.getName());

    public int insert(ConvertJob job) throws Exception {
        String sql = "INSERT INTO convert_jobs (user_id, pdf_path, doc_path, status, title) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, job.getUserId());
            stmt.setString(2, job.getPdfPath());
            stmt.setString(3, job.getDocPath());
            stmt.setString(4, job.getStatus());
            stmt.setString(5, job.getTitle());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0)
                return -1;

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    job.setId(id);
                    return id;
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error in Insert convert job Method", e);
        }
        return -1;
    }

    public boolean updateStatusAndDocPath(int jobId, String status, String docPath) throws Exception {
        String sql = "UPDATE convert_jobs SET status = ?, doc_path = ? WHERE id = ?";
        try (Connection conn = DB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setString(2, docPath);
            stmt.setInt(3, jobId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error in updateStatusAndDocPath Method", e);
        }
        return false;
    }

    public List<ConvertJob> getJobsByUser(int userId) throws Exception {
        List<ConvertJob> list = new ArrayList<>();
        String sql = "SELECT * FROM convert_jobs WHERE user_id = ?";
        try (Connection conn = DB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ConvertJob job = new ConvertJob();
                job.setId(rs.getInt("id"));
                job.setUserId(rs.getInt("user_id"));
                job.setPdfPath(rs.getString("pdf_path"));
                job.setDocPath(rs.getString("doc_path"));
                job.setStatus(rs.getString("status"));
                job.setTitle(rs.getString("title"));
                list.add(job);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error in getJobsByUser Method", e);
        }
        return list;
    }

    public ConvertJob getJobById(int jobId) throws Exception {
        String sql = "SELECT * FROM convert_jobs WHERE id = ?";
        try (Connection conn = DB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, jobId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ConvertJob job = new ConvertJob();
                job.setId(rs.getInt("id"));
                job.setUserId(rs.getInt("user_id"));
                job.setPdfPath(rs.getString("pdf_path"));
                job.setDocPath(rs.getString("doc_path"));
                job.setStatus(rs.getString("status"));
                job.setTitle(rs.getString("title"));
                return job;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error in getJobById Method", e);
        }
        return null;
    }

    public boolean updatePdfPath(int jobId, String newPath) throws Exception {
        String sql = "UPDATE convert_jobs SET pdf_path = ? WHERE id = ?";
        try (Connection conn = DB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newPath);
            stmt.setInt(2, jobId);
            return stmt.executeUpdate() > 0;
        }
    }
}
