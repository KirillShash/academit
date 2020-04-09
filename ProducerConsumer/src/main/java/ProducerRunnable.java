public class ProducerRunnable implements Runnable {
    private final ProducerConsumer producerConsumer;

    public ProducerRunnable(ProducerConsumer producerConsumer) {
        this.producerConsumer = producerConsumer;
    }

    @Override
    public void run() {
        int i = 1;

        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(2000);

                String item = "Item" + i;
                producerConsumer.addItem(item);

                System.out.println("Producer added item: " + item);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
