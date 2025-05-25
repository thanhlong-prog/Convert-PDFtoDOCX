package Controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class DownloadController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String encodedPath = req.getParameter("path");
        if (encodedPath == null || encodedPath.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu đường dẫn file");
            return;
        }

        String filePath = URLDecoder.decode(encodedPath, "UTF-8");
        File file = new File(filePath);

        if (!file.exists() || file.isDirectory()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy file");
            return;
        }

        resp.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
             BufferedOutputStream out = new BufferedOutputStream(resp.getOutputStream())) {

            byte[] buffer = new byte[4096];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        }
    }
}