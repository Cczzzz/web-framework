package test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by chenchang on 2018/12/10.
 */
@Retention(RetentionPolicy.SOURCE)
public  @interface JavacA {
    String name() default "aaa";

}
