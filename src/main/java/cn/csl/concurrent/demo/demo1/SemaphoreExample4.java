package cn.csl.concurrent.demo.demo1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SemaphoreExample4 {
    private final static int threadCount = 20;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < threadCount; i++) {
            final int count = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS)) {
                            test(count);
                            semaphore.release();
                        }
                    } catch (Exception e) {
                        log.info("exception", e);
                    }
                }
            });
        }
        executorService.shutdown();
    }

    private static void test(int count) throws InterruptedException {
        log.info("{}", count);
        Thread.sleep(1000);
    }
}

