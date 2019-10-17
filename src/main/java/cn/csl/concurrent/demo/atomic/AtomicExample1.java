package cn.csl.concurrent.demo.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用原子类实现计数器
 */
@Slf4j
public class AtomicExample1 {
    //请求总数
    private static int cientTotal=5000;
    private static int threadTotal=200;
    //计数器
    private static AtomicInteger count=new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        //线程池
        ExecutorService executorService= Executors.newCachedThreadPool();
        //信号量
        final Semaphore semaphore=new Semaphore(threadTotal);
        //闭锁
        final CountDownLatch countDownLatch=new CountDownLatch(cientTotal);
        for(int i=0;i<cientTotal;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                       semaphore.acquire();
                        add();
                        semaphore.release();
                    }catch (Exception e){
                        log.error("exception", e);
                    }
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        //要在全部请求执行完之后再打印，那么只需在这句话前面加countDownLatch.await();
        log.info("count:{}", count.get());
    }
    private static void add(){
        count.getAndIncrement();
    }
}
