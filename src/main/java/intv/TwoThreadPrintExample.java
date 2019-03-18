package intv;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 *  问题场景：
 *     有两个线程A和线程B，线程A只能打印奇数，线程B只能打印偶数。
 *     要求有序打印 1, 2, 3, 4, ..., 97, 98, 99, 100
 * </pre>
 */
public class TwoThreadPrintExample {
    private final Lock lock = new ReentrantLock(true);
    private final Condition oddNumberCondition = lock.newCondition();
    private final Condition evenNumberCondition = lock.newCondition();
    private final CountDownLatch startGate = new CountDownLatch(1);

    private static final int MAX_NUM = 101;
    private int num = 1;

    public void printNumber() {
        Thread oddNumberThread = new Thread(() -> {
            while (num < MAX_NUM) {
                lock.lock();
                try {
                    System.out.print(num + " ");
                    num++;

                    // 奇数打印线程：从运行状态->阻塞等待
                    oddNumberCondition.await();
                    // 偶数打印线程：从阻塞等待->唤醒状态
                    evenNumberCondition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "oddNumberThread");
        Thread evenNumberThread = new Thread(() -> {
            try {
                startGate.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (num < MAX_NUM) {
                lock.lock();
                try {
                    System.out.print(num + " ");
                    num++;

                    // 奇数打印线程：从阻塞等待->唤醒状态
                    oddNumberCondition.signal();
                    // 奇数打印线程：从运行状态->阻塞等待
                    evenNumberCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "evenNumberThread");

        oddNumberThread.start();
        evenNumberThread.start();
        startGate.countDown();
    }

    public static void main(String[] args) {
        new TwoThreadPrintExample().printNumber();
    }
}
