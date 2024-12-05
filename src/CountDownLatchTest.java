import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    private static final CountDownLatch latch = new CountDownLatch(3);

    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + " is working.");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }

            latch.countDown();
            System.out.println(Thread.currentThread().getName() + " is finished.");
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace(System.err);
        }

        System.out.println("All threads have finished their work.");
    }
}
