package helpers;

import java.util.concurrent.Semaphore;

public class Synchronization implements Runnable {
    private final Semaphore semaphore;
    private final boolean callSignal;

    public Synchronization(Semaphore semaphore, boolean callSignal) {
        this.semaphore = semaphore;
        this.callSignal = callSignal;
    }

    public static void main(String[] s) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);

        Thread thread1 = new Thread(new Synchronization(semaphore, true));
        thread1.start();
        Thread thread2 = new Thread(new Synchronization(semaphore, false));
        thread2.start();
        Thread thread3 = new Thread(new Synchronization(semaphore, false));
        thread3.start();
        Thread thread4 = new Thread(new Synchronization(semaphore, false));
        thread4.start();
    }

    @Override
    public void run() {
        System.out.println("Hello " + Thread.currentThread().getName());
        semaphore.acquireUninterruptibly();
        System.out.println("Acquired by " + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        semaphore.release();
        System.out.println("Released by " + Thread.currentThread().getName());
    }
}
