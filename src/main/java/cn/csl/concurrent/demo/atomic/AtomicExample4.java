package cn.csl.concurrent.demo.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class AtomicExample4 {
    //基本类型
    /*private static AtomicReference<Integer>count=new AtomicReference(0);
    public static void main(String[] args) {
        count.compareAndSet(0,1);//如果count的值等于0，那么将其更新为1
        count.compareAndSet(0,2);
        count.compareAndSet(1,3);//如果count的值等于1，那么将其更新为3
        count.compareAndSet(2,4);
        log.info("count:{}",count);//结果是3
    }*/
    //引用类型
    public static void main(String[] args) {
        // 创建两个Person对象，它们的id分别是101和102。
        Person p1 = new Person(101);
        Person p2 = new Person(102);
        // 新建AtomicReference对象，初始化它的值为p1对象
        AtomicReference ar = new AtomicReference(p1);
        Person p3 = (Person) ar.get();
        System.out.println("p3 is " + p3);
        System.out.println("p3.equals(p1)=" + p3.equals(p1));//true
        // 通过CAS设置ar。如果ar的值为p1的话，则将其设置为p2。
        ar.compareAndSet(p1, p2);
        Person p4 = (Person) ar.get();
        System.out.println("p4 is " + p4);
        System.out.println("p4.equals(p1)=" + p4.equals(p1));//false
        System.out.println("p4.equals(p2)=" + p4.equals(p2));//true
    }

    static class Person {
        volatile long id;

        public Person(long id) {
            this.id = id;
        }

        public String toString() {
            return "id:" + id;
        }

    }
}

