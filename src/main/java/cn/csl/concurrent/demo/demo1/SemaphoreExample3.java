package cn.csl.concurrent.demo.demo1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 一次获得多个许可，可以实现和单线程一样的效果
 */
@Slf4j
public class SemaphoreExample3 {
    private final static int clientTotal = 20;//请求总数
    private final static int threadTotal = 3;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire(3);
                        test(count);
                        semaphore.release(3);
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

