package cn.csl.concurrent.demo.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class SemaphoreExample1 {
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
                        semaphore.acquire();//每次获取一个许可
                        test(threadNum);
                        semaphore.release();
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
        Thread.sleep(2000);
    }
}
