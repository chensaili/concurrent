package cn.csl.concurrent.demo.singleton;


/*
 * 懒汉模式，双重同步锁单例模式
 * 单例实例在第一次使用时进行创建
 * 使用volatile关键字和双重检测机制，禁止指令重排，使线程变安全
 */
public class SingletonExample5 {
    //私有构造函数,必须是私有，如果是公共，就可以在类的外面通过new创建很多对象
    private SingletonExample5() {
    }
    //单例对象
    private volatile static SingletonExample5 instance = null;

    //静态的工厂方法
    public static SingletonExample5 getInstance() {
        if (instance == null) {//双重检测机制
            synchronized (SingletonExample1.class) {//同步锁
                if (instance == null) {
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }
}
