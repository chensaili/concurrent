package cn.csl.concurrent.demo.threadPool;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
@Slf4j
public class ThreadPoolExample5 {
    public static void main(String[] args) {
        ScheduledExecutorService executorService= Executors.newScheduledThreadPool(5);
        for(int i=0;i<10;i++){
            final int count=i;
            executorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                        log.info("schedule run");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            },1,3, TimeUnit.SECONDS);
        }
    }
}
