package Model.BO;

import java.util.List;

import Model.BEAN.ConvertJob;
import Model.DAO.ConvertJobDAO;

public class ConvertJobBO {
    private ConvertJobDAO dao = new ConvertJobDAO();

    public int addJob(ConvertJob job) throws Exception {
        job.setStatus("Pending");
        return dao.insert(job);
    }

    public boolean updateJobStatus(int jobId, String status, String docPath) throws Exception {
        return dao.updateStatusAndDocPath(jobId, status, docPath);
    }

    public List<ConvertJob> getUserJobs(int userId) throws Exception {
        return dao.getJobsByUser(userId);
    }

    public ConvertJob getJobById(int jobId) throws Exception {
        return dao.getJobById(jobId);
    }
}
