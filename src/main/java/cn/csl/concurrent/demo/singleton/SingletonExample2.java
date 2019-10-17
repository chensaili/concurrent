package cn.csl.concurrent.demo.singleton;



/*
 * 饿汉模式
 * 单例实例在类加载的时候创建
 * 不足：1.如果类的构造方法中存在过多的处理，会使类加载时会很慢
 *       2.如果只进行加载，而没有实际的使用，那么会造成资源的浪费
 */
public class SingletonExample2 {
    //私有构造函数,必须是私有，如果是公共，就可以在类的外面通过new创建很多对象
    private SingletonExample2() {
    }

    //单例对象
    private static SingletonExample2 instance = new SingletonExample2();;

    //静态的工厂方法
    public static SingletonExample2 getInstance() {
        return instance;
    }
}
