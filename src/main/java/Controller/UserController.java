package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.ConvertJob;
import Model.BEAN.UserSessionInfo;
import Model.BO.ConvertJobBO;

@WebServlet("/profile")
public class UserController extends HttpServlet {
    private final ConvertJobBO jobBO = new ConvertJobBO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        UserSessionInfo user = (UserSessionInfo) req.getSession().getAttribute("user");
        if (user == null) {
            res.sendRedirect("login.jsp");
            return;
        }
        int userId = user.getId();

        List<ConvertJob> jobs;
        int convertCount = 0;
        try {
            jobs = jobBO.getUserJobs(userId);
            if (jobs != null) {
                convertCount = jobs.size();
            } else {
                convertCount = 0;
            }
        } catch (Exception ex) {
            System.err.println("Error fetching user jobs: " + ex.getMessage());
        }

        req.setAttribute("convertCount", convertCount);
        req.getRequestDispatcher("profile.jsp").forward(req, res);
    }
}
