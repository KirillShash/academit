public class ConsumerRunnable implements Runnable {
    private final ProducerConsumer producerConsumer;

    public ConsumerRunnable(ProducerConsumer producerConsumer) {
        this.producerConsumer = producerConsumer;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(2000);

                String item = producerConsumer.getItem();
                System.out.println("Taken item: " + item);

                System.out.println("Consumer taken item: " + item);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
