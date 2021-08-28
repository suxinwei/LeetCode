package test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * created by suxinwei at 2019-08-20
 * description:
 */
class ThreadTest {
    private static final ThreadPoolExecutor taskCachedThreadPool = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS, new SynchronousQueue<>());
    private static final ThreadPoolExecutor taskCachedThreadPool2 = new ThreadPoolExecutor(1, 1, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    public static void main(String[] args) {
        taskCachedThreadPool.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//                System.out.println("饱和策略");
                taskCachedThreadPool2.submit(r);
            }
        });
        taskCachedThreadPool2.allowCoreThreadTimeOut(true);
        for (int i = 0; i < 6; i++) {
            int finalI = i;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread() + "开始执行====hashCode:" + Thread.currentThread().hashCode() + ",index:" + finalI);
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    System.out.println(Thread.currentThread() + "结束执行====" + finalI);
                }
            };
            taskCachedThreadPool.submit(thread);
        }
        try {
            Thread.sleep(3 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        taskCachedThreadPool2.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "开始执行====hashCode:" + Thread.currentThread().hashCode());
            }
        });
        taskCachedThreadPool.shutdown();
//        taskCachedThreadPool2.shutdown();
    }
}
