package cn.csl.concurrent.demo.demo1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 计数，通过使用原子类达到线程安全的目的
 */
@Slf4j
public class CountDownLatchExample2 {
    private final static int clientTotal = 500;//请求总数
    private static AtomicInteger count = new AtomicInteger(0);//计数器

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        test();
                    } catch (Exception e) {
                        log.info("exception", e);
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        countDownLatch.await();
        log.info("{}", count);
        executorService.shutdown();
    }

    private static void test() {
        count.getAndIncrement();
    }
}

