import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ProducerConsumer {
    private static final int QUEUE_CAPACITY = 10;

    private final int producersCount;
    private final int consumersCount;

    private final Object lock = new Object();
    private final List<Thread> threads = new ArrayList<>();
    private final Queue<String> queue = new LinkedList<>();

    public ProducerConsumer(int producersCount, int consumersCount) {
        this.producersCount = producersCount;
        this.consumersCount = consumersCount;
    }

    public void addItem(String item) throws InterruptedException {
        synchronized (lock) {
            while (queue.size() >= QUEUE_CAPACITY) {
                lock.wait();
            }

            queue.add(item);
            lock.notifyAll();

            System.out.println("Size: " + queue.size());
        }
    }

    public String getItem() throws InterruptedException {
        synchronized (lock) {
            while (queue.isEmpty()) {
                lock.wait();
            }

            String item = queue.remove();
            lock.notifyAll();

            System.out.println("Size: " + queue.size());

            return item;
        }
    }

    public void start() {
        for (int i = 1; i <= producersCount; i++) {
            Thread t = new Thread(new ProducerRunnable(this));
            threads.add(t);
            t.start();
        }

        for (int i = 1; i <= consumersCount; i++) {
            Thread t = new Thread(new ConsumerRunnable(this));
            threads.add(t);
            t.start();
        }
    }
}
