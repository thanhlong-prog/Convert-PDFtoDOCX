package Controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.ConvertJob;
import Model.BEAN.UserSessionInfo;
import Model.BO.ConvertJobBO;

@WebServlet("/listJobs")
public class ListConvertJobsController extends HttpServlet {
    private final ConvertJobBO  jobBO = new ConvertJobBO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserSessionInfo user = (UserSessionInfo) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        int userId = user.getId();
        List<ConvertJob> jobs;
        try {
            jobs = jobBO.getUserJobs(userId);
            for (ConvertJob job : jobs) {
                if ("Completed".equals(job.getStatus())) {
                    String encodedPath = URLEncoder.encode(job.getDocPath(), "UTF-8");
                    job.setDocPath("download?path=" + encodedPath);
                    job.setStatus("Hoàn thành");
                }
                if("Failed".equals(job.getStatus())) {
                    job.setStatus("Thất bại");
                }
                if("Pending".equals(job.getStatus())) {
                    job.setStatus("Đang chờ xử lý");
                }
                if("Processing".equals(job.getStatus())) {
                    job.setStatus("Đang xử lý");
                }
            }
            req.setAttribute("jobs", jobs);
        } catch (Exception e) {
            throw new ServletException("Error to get List", e);
        }

        req.getRequestDispatcher("listJobs.jsp").forward(req, resp);
    }
}
