package cn.csl.concurrent.demo.lock;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition
 */
@Slf4j
public class LockExample5 {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reentrantLock.lock();//线程1加入AQS等待队列里
                    log.info("wait signal");//1
                    //所有调用condition.await方法的线程会加入到等待队列中，并且线程状态转换为等待状态
                    condition.await();//线程1从AQS队列里移除，对应的操作就是线程1释放锁；线程1加入condition的等待队列
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("get signal");//4
                reentrantLock.unlock();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();//线程1释放锁之后，线程2获取锁
                log.info("get lock");//2
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                condition.signalAll();//取出线程1在condition的节点放入AQS队列中
                log.info("send signal~");//3
                reentrantLock.unlock();//线程2释放锁，故线程1得到锁
            }
        }).start();
    }
}
