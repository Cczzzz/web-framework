package com.cc.redisFramework.Test;

import com.cc.bean.Student;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class TestPool extends GenericObjectPool<Student> {
    public TestPool(PooledObjectFactory<Student> factory, GenericObjectPoolConfig config) {
        super(factory, config);
    }

    public static void main(String[] args) {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(8);
        config.setMaxTotal(16);
        config.setMinIdle(4);
        config.setMaxWaitMillis(-1);
        config.setBlockWhenExhausted(true);
        config.setTestOnBorrow(true);
        config.setTestOnCreate(true);
        config.setTestOnReturn(true);
        config.setTestWhileIdle(true);
        TestPool pool = new TestPool(new factory(), config);
        new Thread(() -> {
            try {
                //  while (true) {
                Student student = pool.borrowObject();
                System.out.println(student);
                student.setActivity(false);
                pool.returnObject(student);
                Thread.sleep(500);
                //   }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println();
    }


    static class factory implements PooledObjectFactory<Student> {

        @Override
        public PooledObject<Student> makeObject() throws Exception {
            System.out.println("makeObject");
            PooledObject<Student> pooledObject = new DefaultPooledObject(new Student() {{
                setActivity(true);
                setAge(11);
                setName("adada");
            }});
            return pooledObject;
        }

        @Override
        public void destroyObject(PooledObject<Student> p) throws Exception {
            System.out.println("destroyObject");
        }

        @Override
        public boolean validateObject(PooledObject<Student> p) {
            System.out.println("validateObject==" + p.getObject().isActivity());
            return p.getObject().isActivity();
        }

        @Override
        public void activateObject(PooledObject<Student> p) throws Exception {
            System.out.println("activateObject");
        }

        @Override
        public void passivateObject(PooledObject<Student> p) throws Exception {
            System.out.println("passivateObject");
        }
    }
}
