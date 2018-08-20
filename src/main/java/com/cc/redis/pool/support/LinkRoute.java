package com.cc.redis.pool.support;

import java.util.Objects;

public class LinkRoute extends ThreadLocal<String> {

    private static final String READ = "read";

    private static final String WRITE = "write";

    public boolean isRead() {
        return super.get().equals(READ);
    }

    public boolean isWrite() {
        return super.get().equals(WRITE);
    }

    public void setRoute(Type type) {
        super.set(type.value);
    }

    public void setRoute(String type) {
        if (!type.equals(READ) && !type.equals(WRITE)) {
            //todo 抛异常 或者警告
            type = WRITE;
        }
        super.set(type);
    }


    enum Type {
        READ("read"), WRITE("write");
        private String value;

        Type(String value) {
            this.value = value;
        }
    }
}
