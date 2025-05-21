package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Model.BEAN.UserSessionInfo;

@WebServlet("/convert")
@MultipartConfig
public class ConvertJobController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserSessionInfo user = (UserSessionInfo) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        Part filePart = req.getPart("pdfFile");
        String fileName = filePart.getSubmittedFileName();

        try (Socket socket = new Socket("localhost", 5555);
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                InputStream fileInputStream = filePart.getInputStream()) {

            dos.writeInt(user.getId());

            dos.writeUTF(fileName);

            long fileSize = filePart.getSize();
            dos.writeLong(fileSize);

            byte[] buffer = new byte[4096];
            int read;
            while ((read = fileInputStream.read(buffer)) != -1) {
                dos.write(buffer, 0, read);
            }
            dos.flush();

            int jobId = dis.readInt();

            String status = dis.readUTF();
            if ("SUCCESS".equals(status)) {
                String docPath = dis.readUTF();
                req.setAttribute("message", "Chuyển đổi thành công!");
                req.setAttribute("downloadLink", docPath);
            } else {
                req.setAttribute("message", "Chuyển đổi thất bại!");
            }
            req.getRequestDispatcher("home.jsp").forward(req, resp);

        } catch (Exception e) {
            req.setAttribute("message", "Server đang lỏ. Vui lòng thử lại sau!");
            req.getRequestDispatcher("home.jsp").forward(req, resp);
            throw new ServletException("Error connected ConvertServer", e);
        }
    }
}