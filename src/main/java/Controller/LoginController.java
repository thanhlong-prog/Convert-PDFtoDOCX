package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BEAN.User;
import Model.BEAN.UserSessionInfo;
import Model.BO.UserBO;

@WebServlet("/login")
public class LoginController extends HttpServlet{
    private final UserBO userBO = new UserBO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userBO.login(username, password);

        if (user != null) {
            HttpSession session = req.getSession();
            UserSessionInfo userSessionInfo = new UserSessionInfo(user.getId(), user.getUsername(), user.getFullname());
            session.setAttribute("user", userSessionInfo);
            resp.sendRedirect("home");
        } else {
            req.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
