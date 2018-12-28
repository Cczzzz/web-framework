package com.cc.bean;

/**
 * Created by chenchang on 2018/12/27.
 */
public class AA implements Cope<Student, Sthdent2> {


    @Override
    public void ccc(Student student, Sthdent2 sthdent2) {
        sthdent2.setName(student.getName());
        sthdent2.setAge(student.getAge());
        sthdent2.setClassName(student.getClassName());
    }
}
