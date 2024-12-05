import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest rlt = new ReentrantLockTest();

        Runnable runnable = () -> {
            for (int i = 0; i < 100000; i++) {
                rlt.increment();
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace(System.err);
        }

        System.out.println("Count: " + rlt.getCount());
    }
}
