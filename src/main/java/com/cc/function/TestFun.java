package com.cc.function;

import com.cc.bean.Student;

/**
 * Created by chenchang on 2018/10/29.
 */
public class TestFun {

    @FunctionalInterface
    public interface fun<T, R> {
        R apply(T t);

        default R applyA(T t) {
            try {
                return apply(t);
            } catch (Exception e) {
                StackTraceElement[] ste = Thread.currentThread().getStackTrace();
                StackTraceElement element = ste[2];
                System.out.println(element.getClassName() + ";" + element.getMethodName() + ";" + element.getLineNumber());
                throw e;
            }
        }

    }


    static fun<Student, String> function = student -> {

        System.out.println(2/0);
        return student.getName();
    };

    public static void main(String[] args) {
        Student student = new Student();
        student.setName("aaaa");
        System.out.println(function.applyA(student));

    }
}
