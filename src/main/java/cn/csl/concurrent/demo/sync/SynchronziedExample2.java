package cn.csl.concurrent.demo.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronziedExample2 {
    //修饰一个类,作用于所有对象
    public static void test1(int j) {
        synchronized (SynchronziedExample2.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 {}-{}", j, i);
            }
        }
    }

    //修饰一个静态方法,作用于所有对象
    public static synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2 {}-{}", j, i);
        }
    }

    public static void main(String[] args) {
        SynchronziedExample2 example1 = new SynchronziedExample2();
        SynchronziedExample2 example2 = new SynchronziedExample2();
        //使用线程池和两次执行test1方法，这样才能看到一个对象的两个进程同时来调用代码块
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                example1.test2(1);//example1.test2();
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                example1.test2(2);////example1.test2();
            }
        });
    }
}

