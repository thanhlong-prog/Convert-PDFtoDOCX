package Server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppInitializer implements ServletContextListener{
    private Thread workerThread;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        workerThread = new Thread(new JobWorker());
        workerThread.start();
        System.out.println("JobWorker started...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (workerThread != null && workerThread.isAlive()) {
            workerThread.interrupt();
            System.out.println("JobWorker stopped...");
        }
    }
}
