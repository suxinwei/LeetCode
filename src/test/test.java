package test;

import java.util.concurrent.Semaphore;

class test {
    public static void main(String[] args) {
        TestRunnable testRunnable = new TestRunnable();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(testRunnable);
            thread.start();
            try {
//                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    static class TestRunnable implements Runnable {
        final Object mObject = new Object();
        Semaphore mSemaphore = new Semaphore(1);

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "：acquire前");
            try {
                mSemaphore.acquire();
                System.out.println(Thread.currentThread().getName() + "：acquire后");
                new Thread("release线程") {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName() + Thread.currentThread().getId() + "：release前");
                        mSemaphore.release();
                        System.out.println(Thread.currentThread().getName() + Thread.currentThread().getId() + "：release后");
                    }

                }.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
