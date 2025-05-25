package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.BEAN.ConvertJob;
import Model.BO.ConvertJobBO;
import Model.DAO.ConvertJobDAO;
import Utils.Utils;

public class JobWorker implements Runnable {
    private final ConvertJobBO jobBO = new ConvertJobBO();
    private static final Logger logger = Logger.getLogger(ConvertJobDAO.class.getName());

    @Override
    public void run() {
        while (true) {
            try {
                ConvertJob job = JobQueue.takeJob(); 
                processJob(job); 
            } catch (InterruptedException e) {
                break; 
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error in run JobWorker method", e);
            }
        }
    }

    private void processJob(ConvertJob job) throws Exception {
        jobBO.updateJobStatus(job.getId(), "Processing", null);
        File pdfFile = new File(job.getPdfPath());
        if (!pdfFile.exists()) {
            jobBO.updateJobStatus(job.getId(), "Failed", null);
            return;
        }

        try (Socket socket = new Socket("localhost", 5555);
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                FileInputStream fis = new FileInputStream(pdfFile)) {

            dos.writeInt(job.getUserId());
            dos.writeUTF(pdfFile.getName());
            dos.writeLong(pdfFile.length());

            byte[] buffer = new byte[4096];
            int read;
            while ((read = fis.read(buffer)) != -1) {
                dos.write(buffer, 0, read);
            }
            dos.flush();

            String status = dis.readUTF();

            if ("Completed".equals(status)) {
                long fileLength = dis.readLong();
                String docPath = pdfFile.getAbsolutePath().replace(".pdf", ".docx");
                File receivedFile = new File(docPath);

                try (FileOutputStream fos = new FileOutputStream(receivedFile)) {
                    long remaining = fileLength;
                    while (remaining > 0 &&
                            (read = dis.read(buffer, 0, (int) Math.min(buffer.length, remaining))) != -1) {
                        fos.write(buffer, 0, read);
                        remaining -= read;
                    }
                }

                jobBO.updateJobStatus(job.getId(), "Completed", receivedFile.getAbsolutePath());

                Utils.deleteFile(pdfFile.getAbsolutePath());
            } else {
                jobBO.updateJobStatus(job.getId(), "Failed", null);
            }
        } catch (Exception e) {
            jobBO.updateJobStatus(job.getId(), "Failed", null);
             logger.log(Level.SEVERE, "Error in processJob JobWorker method", e);
        }
    }
}
