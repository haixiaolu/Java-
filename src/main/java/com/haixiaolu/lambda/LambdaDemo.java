package com.haixiaolu.lambda;

public class LambdaDemo {

    public static void main(String[] args) {

        // 1. create thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("new thread run method being executed");
            }
        }).start();

        /**
         * 如何用Lambda对上面的代码进行简化呢？
         * 匿名内部类： new Runnable（）
         *
         * 什么情况下可以用lambda简化呢
         *  - 如果这个匿名内部类是一个接口， 并且只有一个抽象方法需要重写，
         *    就可以用lambda简化
         */

        new Thread(()-> {
            System.out.println("New Thread is being executed");
        }).start();
    }
}
