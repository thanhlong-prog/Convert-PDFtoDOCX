package Server;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import Model.BEAN.ConvertJob;

public class JobQueue {
    private static final BlockingQueue<ConvertJob> jobQueue = new LinkedBlockingQueue<>();

    public static void addJob(ConvertJob job) {
        jobQueue.offer(job);
    }

    public static ConvertJob takeJob() throws InterruptedException {
        return jobQueue.take();
    }
}
