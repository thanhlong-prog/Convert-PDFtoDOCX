package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Model.BEAN.ConvertJob;
import Model.BO.ConvertJobBO;
import Model.BO.PdfConvertionHelper;

public class ConvertServer {
    private static final int PORT = 5555;
    private ConvertJobBO jobBO = new ConvertJobBO();

    public static void main(String[] args) {
        new ConvertServer().start();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("ConvertServer is running on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> handleClient(clientSocket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket socket) {
        try (DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {

            int userId = dis.readInt();

            String fileName = dis.readUTF();

            long fileSize = dis.readLong();

            File baseDir = new File("converted");
            File userDir = new File(baseDir, String.valueOf(userId));
            if (!userDir.exists()) {
                userDir.mkdirs();
            }
            File pdfFile = new File(userDir, fileName);

            try (FileOutputStream fos = new FileOutputStream(pdfFile)) {
                byte[] buffer = new byte[4096];
                long remaining = fileSize;
                int read;
                while (remaining > 0 && (read = dis.read(buffer, 0, (int) Math.min(buffer.length, remaining))) != -1) {
                    fos.write(buffer, 0, read);
                    remaining -= read;
                }
            }

            ConvertJob job = new ConvertJob();
            job.setUserId(userId);
            job.setPdfPath(pdfFile.getAbsolutePath());
            job.setStatus("Pending");
            int jobId = jobBO.addJob(job);

            dos.writeInt(jobId);
            dos.flush();

            try {
                PdfConvertionHelper.convertPdfToDoc(pdfFile.getAbsolutePath());
                String docPath = pdfFile.getAbsolutePath().replace(".pdf", ".docx");

                jobBO.updateJobStatus(jobId, "Completed", docPath);
                dos.writeUTF("SUCCESS");
                dos.writeUTF(docPath);
                dos.flush();

                System.out.println("Job id=" + jobId + " completed.");
            } catch (Exception e) {
                jobBO.updateJobStatus(jobId, "Failed", null);
                System.out.println("Job id=" + jobId + " failed.");
                dos.writeUTF("FAILED");
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {
            }
        }
    }
}
