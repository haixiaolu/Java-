package com.haixiaolu.lambda;

import java.util.function.IntPredicate;

/**
 * 现有方法： Intpredicate是一个接口， 先使用匿名内部类的写法调用该方法
 */
public class LambdaDemon3 {
    public static void printNum(IntPredicate predicate){
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i : arr) {
            if (predicate.test(i)){
                System.out.println(i);
            }

        }

    }

    public static void main(String[] args) {
        // 匿名函数
        printNum(new IntPredicate() {
            @Override
            public boolean test(int value) {
                return value % 2 == 0;
            }
        });

        // lambda
        printNum((int value) -> {
            return value % 2 == 0;
        });
    }
}
