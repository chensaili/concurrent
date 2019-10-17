package cn.csl.concurrent.demo.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
//复习用的
@Slf4j
public class FutureExampleDemo {
    static class MyCallable implements Callable<String>{

        @Override
        public String call() throws Exception {
            log.info("do something in callable");
            Thread.sleep(5000);
            return "done";
        }
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService= Executors.newCachedThreadPool();
        Future<String>future=executorService.submit(new MyCallable());
        log.info("do something in main");
        String result=future.get();
        log.info("result:{}",result);

    }
}
