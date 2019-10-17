package cn.csl.concurrent.demo.immutable;

import cn.csl.concurrent.demo.annotations.ThreadSafe;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

@Slf4j
@ThreadSafe
public class ImmutableExample2 {
    private static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        //unmodifiableMap源码需要把更新的方法全部抛出异常
        map = Collections.unmodifiableMap(map);//将一个普通的map变成了一个不可修改的map
    }

    public static void main(String[] args) {
        map.put(1, 3);//允许操作，但操作时会抛出异常，
        log.info("{}", map.get(1));
    }
}
