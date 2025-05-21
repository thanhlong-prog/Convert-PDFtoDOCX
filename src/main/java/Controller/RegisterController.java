package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BO.UserBO;
import Model.User;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private UserBO userBO = new UserBO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");
        String confirmPassword = req.getParameter("confirmPassword");

        if (username == null || password == null || confirmPassword == null || fullname == null ||
            username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || fullname.isEmpty()) {
            req.setAttribute("error", "Vui lòng điền đầy đủ thông tin.");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }

        if (!password.equals(confirmPassword)) {
            req.setAttribute("error", "Mật khẩu xác nhận không khớp.");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }

        User newUser = new User(0, username, password, fullname); 
        boolean success = userBO.register(newUser);

        if (success) {
            resp.sendRedirect("login.jsp");
        } else {
            req.setAttribute("error", "Tên đăng nhập đã tồn tại.");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }
}
