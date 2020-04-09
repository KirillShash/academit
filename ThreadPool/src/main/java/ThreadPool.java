import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ThreadPool {
    private final List<Thread> threads = new ArrayList<>();
    private final Queue<Runnable> tasksQueue = new LinkedList<>();
    private final Object lock = new Object();

    public ThreadPool(int threadsCount) {
        for (int i = 0; i <= threadsCount; i++) {
            Thread t = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    Runnable task;

                    synchronized (lock) {
                        while (tasksQueue.isEmpty()) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                return;
                            }
                        }

                        task = tasksQueue.remove();
                    }

                    task.run();
                }
            });

            threads.add(t);

            t.start();
        }
    }

    public void addTask(Runnable runnable) {
        synchronized (lock) {
            tasksQueue.add(runnable);
            lock.notifyAll();
        }
    }

    public void stop() {
        threads.forEach(Thread::interrupt);
    }

}
