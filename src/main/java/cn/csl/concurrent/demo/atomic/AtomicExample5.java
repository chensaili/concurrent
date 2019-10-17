package cn.csl.concurrent.demo.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class AtomicExample5 {
    private static AtomicBoolean isHappend = new AtomicBoolean(false);
    //表示请求总数
    private static int clientTotal = 5000;
    //表示可以同时并发执行的线程数
    private static int threadTotal = 200;

    public static void main(String[] args) throws InterruptedException {
        //线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        //闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        test();
                        semaphore.release();
                    } catch (Exception e) {
                        log.error("exception", e);
                    }
                    countDownLatch.countDown();
                }
            });
        }
        //只有CountDownLatch减到0时，即下面才不会被阻塞
        countDownLatch.await();
        //关闭线程池
        executorService.shutdown();
        //要在全部请求执行完之后再打印，那么只需在这句话前面加countDownLatch.await();
        log.info("isHapped:{}", isHappend.get());
    }

    private static void test() {
        //保证了if里面的代码只执行一次
        if (isHappend.compareAndSet(false, true)) {
            log.info("execute");
        }
    }
}
