package com.cc.learn.javac;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by chenchang on 2018/12/10.
 */
@Retention(RetentionPolicy.SOURCE)
@interface JavacA {
    String name() default "aaa";

}
