package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.BO.PdfConvertionHelper;
import Model.DAO.ConvertJobDAO;
import Utils.Utils;

public class ConvertServer {
    private static final int PORT = 5555;
    private static final Logger logger = Logger.getLogger(ConvertJobDAO.class.getName());
    private Thread workerThread;

    public static void main(String[] args) {
        new ConvertServer().start();
    }

    public void start() {
        JobWorker worker = new JobWorker();
        workerThread = new Thread(worker);
        workerThread.start();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("ConvertServer is running on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected from IP: " + clientSocket.getInetAddress().getHostAddress());
                new Thread(() -> handleClient(clientSocket)).start();
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error in start method", e);
        }
    }

    private void handleClient(Socket socket) {
        try (DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {

            int userId = dis.readInt();

            String fileName = dis.readUTF();

            long fileSize = dis.readLong();

            File baseDir = new File("handleFiles");
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

            try {
                PdfConvertionHelper.convertPdfToDoc(pdfFile.getAbsolutePath());
                String docPath = pdfFile.getAbsolutePath().replace(".pdf", ".docx");
                dos.writeUTF("Completed");
                dos.flush();

                File fileToSend = new File(docPath);
                try (FileInputStream fis = new FileInputStream(fileToSend)) {
                    dos.writeLong(fileToSend.length());
                    dos.flush();

                    byte[] buffer = new byte[4096];
                    int read;
                    while ((read = fis.read(buffer)) != -1) {
                        dos.write(buffer, 0, read);
                    }
                    dos.flush();
                }
                Utils.deleteFile(docPath);
                Utils.deleteFile(pdfFile.getAbsolutePath());
            } catch (Exception e) {
                dos.writeUTF("Failed");
                dos.flush();
                Utils.deleteFile(pdfFile.getAbsolutePath());
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in handleClient", e);
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {
            }
        }
    }
}
