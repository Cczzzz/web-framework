package com.cc.toolkit.customFunction;

import com.cc.bean.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.function.Function;

/**
 * Created by chenchang on 2018/10/11.
 */
@Setter
@Getter
public class ListMatch<T> implements Iterable<ListMatch.both<T>> {
    private Collection<T> pList;

    private Function<T, Object> keyFun;

    public ListMatch(Collection<T> pList) {
        this.pList = pList;
    }

    private Map<Object, T> map;


    public static <T> ListMatch<T> of(Collection<T> start, Collection<T> end, Function<T, Object> keyFun) {
        return of(start, end, keyFun, true);
    }

    /**
     * @param start      集合a
     * @param end        集合b
     * @param keyFun     映射得key
     * @param <T>
     * @param endPrimary 是否以集合b为主
     * @return
     */
    public static <T> ListMatch<T> of(Collection<T> start, Collection<T> end, Function<T, Object> keyFun, boolean endPrimary) {
        ListMatch<T> match;
        Map<Object, T> map = new HashMap<>();
        if (endPrimary) {
            match = new ListMatch<>(end);
            for (T t : start) {
                map.put(keyFun.apply(t), t);
            }

        } else {
            match = new ListMatch<>(start);
            for (T t : end) {
                map.put(keyFun.apply(t), t);
            }
        }
        match.setMap(map);
        match.setKeyFun(keyFun);
        return match;
    }

    public Collection<T> getElse() {
        return map.values();
    }

    @Override
    public Iterator<both<T>> iterator() {
        return new both<>(pList, map, keyFun);
    }

    public static class both<T> implements Iterator<both<T>> {
        private Map<Object, T> map;
        private Function<T, Object> keyFun;
        private Iterator<T> pIterator;
        private T main;
        private T minor;

        public both(Collection<T> pList, Map<Object, T> map, Function<T, Object> keyFun) {
            this.map = map;
            this.keyFun = keyFun;
            this.pIterator = pList.iterator();
        }

        @Override
        public boolean hasNext() {
            return pIterator.hasNext();
        }

        @Override
        public both<T> next() {
            this.main = pIterator.next();
            Object key = keyFun.apply(main);
            minor = map.get(key);
            map.remove(key);
            return this;
        }

        public T getMain() {
            return main;
        }

        public T getMinor() {
            return minor;
        }

        public Collection<T> getElse() {
            return map.values();
        }

    }

    //example
    public static void main(String[] args) {
        Student student1 = new Student();
        student1.setName("a");
        student1.setClassName("c1");
        Student student2 = new Student();
        student2.setName("b");
        student2.setClassName("c1");
        Student student3 = new Student();
        student3.setName("c");
        student3.setClassName("c2");
        Student student4 = new Student();
        student4.setName("d");
        student4.setClassName("c2");
        Student student5 = new Student();
        student5.setName("e");
        student5.setClassName("c3");
        List<Student> a = Arrays.asList(student1, student3, student5);
        List<Student> b = Arrays.asList(student2, student4);
        ListMatch<Student> boths = ListMatch.of(a, b, Student::getClassName);
        for (both<Student> both : boths) {
            System.out.println("Main：" + both.getMain());
            System.out.println("Minor：" + both.getMinor());
            System.out.println("---------------------------------");
        }
        for (Student student : boths.getElse()) {
            System.out.println("else:" + student);
        }

        System.out.println("\n********************************\n");
        ListMatch<Student> both2 = ListMatch.of(a, b, Student::getClassName, false);
        for (both<Student> both : both2) {
            System.out.println("Main：" + both.getMain());
            System.out.println("Minor：" + both.getMinor());
            System.out.println("---------------------------------");
        }
    }


}
