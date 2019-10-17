package cn.csl.concurrent.demo.syncContainer;


import cn.csl.concurrent.demo.annotations.NotThreadSafe;

import java.util.Vector;

/**
 * 说明同步容器不一定就是线程安全的
 */
@NotThreadSafe
public class VectorExample2 {
    private static Vector<Integer> vector = new Vector();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }
            //定义两个线程
            Thread thread1 = new Thread() {
                public void run() {
                        for (int i = 0; i < vector.size(); i++) {
                            vector.remove(i);
                        }
                }
            };
            Thread thread2 = new Thread() {
                public void run() {
                        for (int i = 0; i < vector.size(); i++) {
                            vector.get(i);
                        }
                    }

            };
            //比如当一个线程运行到get(9),而另一个线程运行到了remove（9），正好把i=9的元素移除，故发生越界异常
            thread1.start();
            thread2.start();
        }
    }
}
