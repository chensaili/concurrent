package cn.csl.concurrent.demo.syncContainer;

import cn.csl.concurrent.demo.annotations.NotThreadSafe;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
/**
 * 说明同步容器不一定就是线程安全的
 */
@Slf4j
@NotThreadSafe
public class CollectionsExample4 {
    private static List<Integer> list = Collections.synchronizedList(Lists.newArrayList());

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                list.add(i);
            }
            //定义两个线程
            Thread thread1 = new Thread() {
                public void run() {
                    for (int i = 0; i < list.size(); i++) {
                        list.remove(i);
                    }
                }
            };
            Thread thread2 = new Thread() {
                public void run() {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i);
                    }
                }
            };
            //比如当一个线程运行到get(9),而另一个线程运行到了remove（9），正好把i=9的元素移除，故发生越界异常
            thread1.start();
            thread2.start();
        }
    }
}
