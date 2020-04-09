public class Main {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(3);

        for (int i = 1; i < 100; i++) {
            int j = i;
            threadPool.addTask(() -> System.out.println("Print " + j + " , thread id = " + Thread.currentThread().getId()));
        }
    }
}
