package com.haixiaolu.lambda;

import java.util.function.IntBinaryOperator;

public class LambdaDemo2 {

    public static int calculateNum(IntBinaryOperator operator){
        int a = 10;
        int b = 20;
        return operator.applyAsInt(a, b);
    }

    public static void main(String[] args) {

        // before lambda
        int num  = calculateNum(new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left + right;
            }
        });
        System.out.println(num);

        // after lambda
        int num1 = calculateNum((a, b) -> a + b);
        System.out.println(num1);
    }
}
