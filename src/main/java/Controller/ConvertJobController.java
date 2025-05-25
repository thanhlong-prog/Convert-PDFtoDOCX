package Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Model.BEAN.ConvertJob;
import Model.BEAN.UserSessionInfo;
import Model.BO.ConvertJobBO;
import Server.JobQueue;

@WebServlet("/convert")
@MultipartConfig
public class ConvertJobController extends HttpServlet {
    private final ConvertJobBO jobBO = new ConvertJobBO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserSessionInfo user = (UserSessionInfo) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        Part filePart = req.getPart("pdfFile");
        String fileName = filePart.getSubmittedFileName();

        File baseDir = new File("d://converted");
        File userDir = new File(baseDir, String.valueOf(user.getId()));
        if (!userDir.exists()) {
            userDir.mkdirs();
        }
        File savedFile = new File(userDir, fileName);
        try (
                InputStream input = filePart.getInputStream();
                FileOutputStream fos = new FileOutputStream(savedFile)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }

        ConvertJob job = new ConvertJob();
        job.setUserId(user.getId());
        job.setTitle(fileName);
        job.setPdfPath(savedFile.getAbsolutePath());
        job.setStatus("Pending");
        int jobId;
        try {
            jobId = jobBO.addJob(job);
            String newFileName = fileName.replaceAll("(?i)\\.pdf$", "") + "_" + jobId + ".pdf";
            File renamedPdfFile = new File(userDir, newFileName);
            boolean renamed = savedFile.renameTo(renamedPdfFile);
            if (!renamed) {
                System.err.println("Không thể đổi tên file PDF.");
            }
            job.setPdfPath(renamedPdfFile.getAbsolutePath());
            jobBO.updatePdfPath(jobId, renamedPdfFile.getAbsolutePath());
            JobQueue.addJob(job);
            resp.sendRedirect("listJobs");
        } catch (Exception ex) {
            req.setAttribute("error", "Không xử lý được file PDF đã gửi. Vui lòng thử lại sau.");
            req.getRequestDispatcher("home").forward(req, resp);
        }
    }
}