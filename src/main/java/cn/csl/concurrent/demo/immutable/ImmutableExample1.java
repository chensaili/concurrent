package cn.csl.concurrent.demo.immutable;

import cn.csl.concurrent.demo.annotations.NotThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@NotThreadSafe
public class ImmutableExample1 {
    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();//可变

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
        //a=2;报错
        //b="3";报错
        // map=Maps.newHashMap();报错,被final修饰的引用对象不能修改地址
        map.put(1, 3);//但是可以修改值
        log.info("{}", map.get(1));
    }
}
