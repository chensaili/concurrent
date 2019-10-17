package cn.csl.concurrent.demo.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.LongAdder;

/**
 * 演示一个线程安全的类
 */
@Slf4j
public class AtomicExample3 {
    //表示请求总数
    private static int clientTotal = 5000;
    //表示可以同时并发执行的线程数
    private static int threadTotal = 200;
    //计数器，使用原子类
    private static LongAdder count = new LongAdder();//默认值为0

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
                        //如果上面那行代码执行不成功，即没有获得执行的进程，那么这句代码会被阻塞
                        add();
                        //释放进程
                        semaphore.release();
                    } catch (Exception e) {
                        log.error("exception", e);
                    }
                    //try里面的操作每执行完一次之后，countDownLatch就减一
                    countDownLatch.countDown();
                }
            });
        }
        //只有CountDownLatch减到0时，即下面才不会被阻塞
        countDownLatch.await();
        //关闭线程池
        executorService.shutdown();
        //要在全部请求执行完之后再打印，那么只需在这句话前面加countDownLatch.await();
        log.info("count:{}", count);
    }

    private static void add() {
        count.increment();
    }
}



