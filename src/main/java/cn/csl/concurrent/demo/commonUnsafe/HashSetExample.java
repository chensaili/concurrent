package cn.csl.concurrent.demo.commonUnsafe;

import cn.csl.concurrent.demo.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class HashSetExample {

    //表示请求总数
    private static int clientTotal = 5000;
    //表示可以同时并发执行的线程数
    private static int threadTotal = 200;

    private static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws InterruptedException {
        //线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        //闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        //调用 clientTotal次update方法
                        update(count);
                        semaphore.release();
                    } catch (Exception e) {
                        log.error("exception", e);
                    }
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size:{}", set.size());
    }

    private static void update(int i) {
        set.add(i);
    }
}
