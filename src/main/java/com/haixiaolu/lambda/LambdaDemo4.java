package com.haixiaolu.lambda;

import java.util.function.Function;

public class LambdaDemo4 {
    public static <R> R typeConver(Function<String, R> function){
        String str = "1235";
        R result = function.apply(str);
        return result;
    }

    public static void main(String[] args) {
        // 匿名函数
        Integer result = typeConver(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.valueOf(s);
            }
        });

        // lambda
        Integer result1 = typeConver((String s) -> {
                return Integer.valueOf(s);
        });
        System.out.println(result);
        System.out.println(result1);
    }
}
