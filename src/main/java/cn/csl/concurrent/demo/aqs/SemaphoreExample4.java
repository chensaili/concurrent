package cn.csl.concurrent.demo.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SemaphoreExample4 {
    private final static int threadCount = 20;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);//同一时间只能有三个线程并发执行
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        //下面代码表示，会在五秒中不断获取许可。如果不加时间就表示仅一个时刻获取许可
                        //就这个程序来说，一秒只能执行三个线程（因为test方法里sleep了一秒），现在执行了五秒，故一共执行了15条线程
                        if (semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS)) {
                            test(threadNum);
                            semaphore.release();
                        }
                    } catch (Exception e) {
                        log.error("exception", e);
                    }
                }
            });
        }
        exec.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("{}", threadNum);
        Thread.sleep(1000);
    }
}
