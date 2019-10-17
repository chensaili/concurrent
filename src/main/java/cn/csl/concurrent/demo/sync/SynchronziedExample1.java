package cn.csl.concurrent.demo.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*@Slf4j
public class SynchronziedExample1 {

    //修饰一个代码块
    public void test1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1-{}", i);
            }
        }
    }

    //修饰一个方法
    //注意：如果SynchronziedExample1是一个父类，它被子类继承后的test2不带synchronized,如果子类也想使用它被子类继承后的test2不带synchronized，必须重新显式声明
    public synchronized void test2() {
        for (int i = 0; i < 10; i++) {
            log.info("test2-{}", i);
        }
    }

    public static void main(String[] args) {
        SynchronziedExample1 example1 = new SynchronziedExample1();
        //使用线程池和两次执行test1方法，这样才能看到一个对象的两个进程同时来调用代码块
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                example1.test1();//example1.test2();
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                example1.test1();////example1.test2();
            }
        });
    }
}*/

//演示作用的对象是当前对象
@Slf4j
public class SynchronziedExample1 {
    //修饰一个代码块
    public void test1(int j){
        synchronized (this){
            for(int i=0;i<10;i++){
                log.info("test1 -{}-{}",j,i);
            }
        }
    }
    //修饰一个方法
    public synchronized void test2(int j){
        for(int i=0;i<10;i++){
            log.info("test2-{}-{}",j,i);
        }
    }
    public static void main(String[] args) {
        SynchronziedExample1 example1=new SynchronziedExample1();
        SynchronziedExample1 example2=new SynchronziedExample1();
        ExecutorService executorService= Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                example1.test1(1);//example1.test2();
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                example1.test1(2);////example1.test2();
            }
        });
    }
}

