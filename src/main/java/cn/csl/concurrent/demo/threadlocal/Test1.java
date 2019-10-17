package cn.csl.concurrent.demo.threadlocal;

public class Test1 {

    ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
    ThreadLocal<String> stringLocal = new ThreadLocal<String>();


    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }
    public static void main(String[] args) throws InterruptedException {
        /**
         * main线程
         */
        final Test1 test = new Test1();

        test.set();
        System.out.println(test.getLong());
        System.out.println(test.getString());

        /**
         * 其他线程thread1
         */
        Thread thread1 = new Thread(() -> {
            test.set();
            System.out.println(test.getLong());
            System.out.println(test.getString());
        });
        thread1.start();
        thread1.join();

        System.out.println(test.getLong());
        System.out.println(test.getString());
    }
}


