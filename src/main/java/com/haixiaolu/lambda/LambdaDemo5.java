package com.haixiaolu.lambda;

import java.util.function.IntConsumer;

public class LambdaDemo5 {
    public static void foreachArr(IntConsumer consumer){
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i : arr) {
            consumer.accept(i);
        }
    }

    public static void main(String[] args) {
        // 匿名函数
        foreachArr(new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println(value);
            }
        });
        // lambda
        foreachArr((int value) -> {
                System.out.println(value);
        });
    }
}
