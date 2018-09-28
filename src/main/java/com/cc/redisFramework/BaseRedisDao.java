package com.cc.redisFramework;

import com.cc.redisFramework.pool.JedisWritePool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class BaseRedisDao<T> implements RedisDao<T> {

    private final static Logger LOGGER = LoggerFactory.getLogger(BaseRedisDao.class);

    static Pool<Jedis> jedisPool = new JedisWritePool();

    @Override
    public void saveToRedis(T t) {
        String RedisKey = getRedisKey(t);
        Jedis jedis = jedisPool.getResource();
        jedis.hmset(RedisKey, toMap(t));
    }

    @Override
    public T getFromRedis() {
        return null;
    }

    /**
     * 对象转map
     *
     * @return
     */
    private Map<String, String> toMap(T t) {
        HashMap<String, String> map = new HashMap<>();
        Class clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            RedisColumn anno = field.getAnnotation(RedisColumn.class);
            Object value = null;
            try {
                value = field.get(this);
            } catch (IllegalAccessException e) {
                LOGGER.error("字段获取失败");
                e.printStackTrace();
            }
            if (anno != null) {
                if (value == null) {
                    value = "";
                }
                map.put(field.getName(), value.toString());
            }
        }
        return map;
    }

    private static <T> void loadFromMap(Map<String, String> fieldValueMap, T t) {
        try {
            Iterator e = fieldValueMap.keySet().iterator();

            while (e.hasNext()) {
                String key = (String) e.next();
                Field f = null;
                try {
                    f = t.getClass().getDeclaredField(key);
                } catch (Exception var6) {
                    continue;
                }
                f.setAccessible(true);
                String value = (String) fieldValueMap.get(key);
                if (f.getType() == String.class) {
                    f.set(t, value);
                } else if (value != null && !value.equals("")) {
                    if (f.getType() == Integer.class) {
                        f.set(t, Integer.valueOf(Integer.parseInt(value)));
                    } else if (f.getType() == Double.class) {
                        f.set(t, Double.valueOf(Double.parseDouble(value)));
                    } else if (f.getType() == Long.class) {
                        f.set(t, Long.valueOf(Long.parseLong(value)));
                    }
                }
            }

        } catch (Exception var7) {
            var7.printStackTrace();
        }
    }
}
