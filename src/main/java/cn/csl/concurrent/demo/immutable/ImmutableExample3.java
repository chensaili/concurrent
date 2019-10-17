package cn.csl.concurrent.demo.immutable;

import cn.csl.concurrent.demo.annotations.ThreadSafe;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;


@ThreadSafe
public class ImmutableExample3 {
    private final static ImmutableList list = ImmutableList.of(1, 2, 3);
    private final static ImmutableSet set = ImmutableSet.copyOf(list);
    private final static ImmutableMap<Integer, Integer> map1 = ImmutableMap.of(1, 2, 3, 4);
    private final static ImmutableMap<Integer, Integer> map2 = ImmutableMap.<Integer, Integer>builder().put(1, 2).put(3, 4).put(5, 6).build();

    public static void main(String[] args) {
        list.add(4);//异常，这个list不允许修改
        map1.put(1, 4);//异常
    }
}
