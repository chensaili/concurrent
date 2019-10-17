package cn.csl.concurrent.demo.publish;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Escape {
    private int thisCanBeEscape = 0;

    public Escape() {
        new InnerClass();//在构造函数中把内部类发布了出去
    }

    private class InnerClass {
        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
