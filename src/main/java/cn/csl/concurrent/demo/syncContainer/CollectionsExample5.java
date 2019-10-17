package cn.csl.concurrent.demo.syncContainer;


import cn.csl.concurrent.demo.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;
@Slf4j
@ThreadSafe
public class CollectionsExample5 {
    private static Map<Integer, Integer> map = Collections.synchronizedMap(new Hashtable<>());

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                map.put(i,i);
            }
            //定义两个线程
            Thread thread1 = new Thread() {
                public void run() {
                    for (int i = 0; i < map.size(); i++) {
                        map.remove(i);
                    }
                }
            };
            Thread thread2 = new Thread() {
                public void run() {
                    for (int i = 0; i < map.size(); i++) {
                        map.get(i);
                    }
                }
            };
            thread1.start();
            thread2.start();
        }
    }
}

