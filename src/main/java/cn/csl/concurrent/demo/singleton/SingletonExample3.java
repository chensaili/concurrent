package cn.csl.concurrent.demo.singleton;


/*
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 * 使用了synchronized虽然能保证线程安全，但是一次只能有一个线程访问，带来了性能的开销
 */
public class SingletonExample3 {
    //私有构造函数,必须是私有，如果是公共，就可以在类的外面通过new创建很多对象
    private SingletonExample3() {
    }

    //单例对象
    private static SingletonExample3 instance = null;

    //静态的工厂方法
    public static synchronized SingletonExample3 getInstance() {
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }
}
