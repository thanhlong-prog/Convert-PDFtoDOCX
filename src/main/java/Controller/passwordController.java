package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.UserSessionInfo;
import Model.BO.UserBO;

@WebServlet("/password")
public class passwordController extends HttpServlet{
    
    UserBO userBO = new UserBO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserSessionInfo user = (UserSessionInfo) req.getSession().getAttribute("user");
        if (user == null) {
            res.sendRedirect("login.jsp");
            return;
        }
        // String newPassword = req.getParameter("newPassword");
        // if(userBO.updatePassword(user.getId(), newPassword)) {
        //     req.setAttribute("message", "Mật khẩu đã được cập nhật thành công.");
        // } else {
        //     req.setAttribute("error", "Cập nhật mật khẩu không thành công. Vui lòng thử lại.");
        // }
        req.getRequestDispatcher("change_password.jsp").forward(req, res);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserSessionInfo user = (UserSessionInfo) req.getSession().getAttribute("user");
        if (user == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("reTypePassword");
        if (newPassword == null || newPassword.trim().isEmpty()) {
            req.setAttribute("error", "Mật khẩu mới không được để trống.");
            req.getRequestDispatcher("change_password.jsp").forward(req, res);
            return;
        }

        if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
            req.setAttribute("error", "Xác nhận mật khẩu không được để trống.");
            req.getRequestDispatcher("change_password.jsp").forward(req, res);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            req.setAttribute("error", "Mật khẩu xác nhận không khớp.");
            req.getRequestDispatcher("change_password.jsp").forward(req, res);
            return;
        }

        boolean updated = userBO.updatePassword(user.getId(), newPassword);
        if (updated) {
            req.setAttribute("message", "Mật khẩu đã được cập nhật thành công.");
        } else {
            req.setAttribute("error", "Cập nhật mật khẩu không thành công. Vui lòng thử lại.");
        }

        req.getRequestDispatcher("profile.jsp").forward(req, res);
    }
}
