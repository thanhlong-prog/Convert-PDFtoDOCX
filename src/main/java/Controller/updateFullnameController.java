package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.UserSessionInfo;
import Model.BO.UserBO;

@WebServlet("/changeFullname")
public class updateFullnameController extends HttpServlet{
    UserBO userBO = new UserBO();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        UserSessionInfo user = (UserSessionInfo) req.getSession().getAttribute("user");
        if (user == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        String newFullname = req.getParameter("newFullname");
        if (newFullname == null || newFullname.trim().isEmpty()) {
            req.setAttribute("error", "Tên mới không được để trống.");
            req.getRequestDispatcher("profile.jsp").forward(req, res);
            return;
        }

        boolean updated = userBO.updateFullname(user.getId(), newFullname);
        if (updated) {
            user.setFullname(newFullname); 
            req.getSession().setAttribute("user", user);
            req.setAttribute("message", "Cập nhật tên thành công.");
        } else {
            req.setAttribute("error", "Cập nhật tên không thành công.");
        }
        req.getRequestDispatcher("profile.jsp").forward(req, res);
    }
}
