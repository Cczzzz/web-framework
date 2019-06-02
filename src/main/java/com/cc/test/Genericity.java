package com.cc.test;

public class Genericity<T> {

    public T test1(T t) {
        return t;
    }

    public static  <V> V  test2(V v){
        return v;
    }

    public static void main(String[] args) {
        String s1 = Genericity.test2("aaa");

        Genericity<Integer> genericity = new Genericity<>();
        genericity.test1(1);

    }
}
