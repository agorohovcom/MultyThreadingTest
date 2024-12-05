import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    private static final CyclicBarrier barrier = new CyclicBarrier(3, () -> {
        System.out.println("All threads have reached the barrier.");

    });

    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + " is working.");

            try {
                Thread.sleep(1000);
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }

            System.out.println(Thread.currentThread().getName() + " finished.");
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
