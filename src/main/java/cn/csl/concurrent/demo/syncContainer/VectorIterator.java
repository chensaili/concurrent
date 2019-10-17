package cn.csl.concurrent.demo.syncContainer;

import java.util.Iterator;
import java.util.Vector;

public class VectorIterator {
    private static Vector<Integer> vector = new Vector();
    public static void main(String[] args) {
        //添加 100 个元素到容器
        for(int i = 0; i < 100; i++) {
            vector.add(i);
        }
        //利用迭代器遍历，在遍历的同时删除一个元素
        Iterator it = vector.iterator();
        while(it.hasNext()) {
            Integer integer = (Integer) it.next();
            if(integer == 5) {
                it.remove(); //删除一个元素
            }
        }
        System.out.println(vector.toString());
    }
}
