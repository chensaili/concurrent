package cn.csl.concurrent.demo.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolExample4 {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        /*executorService.schedule(new Runnable() {
            @Override
            public void run() {
                log.warn("schedule run");
            }
        },3, TimeUnit.SECONDS);//就是run方法延迟三秒执行*/
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.warn("schedule run");
            }
        }, 1, 3, TimeUnit.SECONDS);//当任务都放进去之后延迟一秒之后，每隔三秒去执行里面的任务
        //因为上面的任务会一直执行下去，所以不需要关闭线程池
        //executorService.shutdown();

        //下面的和上面的功能一样
     /*   Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.warn("timer run");
            }
        }, new Date(), 5 * 1000);*/
    }
}
