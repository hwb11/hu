package com.zy.intelligentdevice.common.redis;

public interface RedisService {

    //设置值+过期时间
    boolean set(final String key, final String value, final int expire);

    //设置值
    boolean set(final String key, final String value);

    //获取值
    String get(final String key);

    //获取自增
    long increment(final String key);

    //设置过期时间
    boolean expire(final String key, long expire);

    //推送消息
    long lpush(final String key, Object obj);

    long rpush(final String key, Object obj);

    //接收消息
    String lpop(final String key);

    //删除key
    Boolean delKey(final String key);

    //设置值
    boolean setNx(final String key, final Object value);

    //获取值
    Object getSet(final String key, final Object value);

    //设置过期时间
    boolean setNx(String key, String value, int expiredTime);

    //获取过期时间
    long getExpire(final String key);

    //删除所有key
    Long delKeys(final String keys);
}
