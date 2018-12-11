package com.cc.lambda;

import com.cc.bean.Student;

import java.util.function.Function;

/**
 * Created by chenchang on 2018/11/30.
 */
public class test {

    public static void main(String[] args) {
        Student student = new Student();
        Function<Student, String> a = Student::getClassName;
        a.apply(student);

    }
}
