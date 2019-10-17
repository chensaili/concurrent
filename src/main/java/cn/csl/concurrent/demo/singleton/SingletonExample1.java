package cn.csl.concurrent.demo.singleton;

/*
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 */
public class SingletonExample1 {
    //私有构造函数,必须是私有，如果是公共，就可以在类的外面通过new创建很多对象
    private SingletonExample1() {
    }

    //单例对象
    private static SingletonExample1 instance = null;

    //静态的工厂方法
    public static SingletonExample1 getInstance() {
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }
}
