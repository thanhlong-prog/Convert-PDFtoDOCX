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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserSessionInfo user = (UserSessionInfo) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        // String newPassword = req.getParameter("newPassword");
        // if(userBO.updatePassword(user.getId(), newPassword)) {
        //     req.setAttribute("message", "Mật khẩu đã được cập nhật thành công.");
        // } else {
        //     req.setAttribute("error", "Cập nhật mật khẩu không thành công. Vui lòng thử lại.");
        // }
        req.getRequestDispatcher("change_password.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserSessionInfo user = (UserSessionInfo) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("reTypePassword");
        if (newPassword == null || newPassword.trim().isEmpty()) {
            req.setAttribute("error", "Mật khẩu mới không được để trống.");
            req.getRequestDispatcher("change_password.jsp").forward(req, resp);
            return;
        }

        if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
            req.setAttribute("error", "Xác nhận mật khẩu không được để trống.");
            req.getRequestDispatcher("change_password.jsp").forward(req, resp);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            req.setAttribute("error", "Mật khẩu xác nhận không khớp.");
            req.getRequestDispatcher("change_password.jsp").forward(req, resp);
            return;
        }

        boolean updated = userBO.updatePassword(user.getId(), newPassword);
        if (updated) {
            req.setAttribute("message", "Mật khẩu đã được cập nhật thành công.");
        } else {
            req.setAttribute("error", "Cập nhật mật khẩu không thành công. Vui lòng thử lại.");
        }

        req.getRequestDispatcher("profile.jsp").forward(req, resp);
    }
}
