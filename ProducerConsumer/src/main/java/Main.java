public class Main {
    public static void main(String[] args) {
        ProducerConsumer producerConsumer = new ProducerConsumer(3, 2);
        producerConsumer.start();
    }
}
