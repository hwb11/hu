package com.zy.intelligentdevice.common.redis;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/*
    redis 分布式锁
 */
@Component("redisLockUtil")
public class RedisLockUtil {

    @Resource
    private RedisService redisServiceImpl;


    /**
     * 加锁
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public boolean lock(String key,String value,int expire) {
        boolean status = redisServiceImpl.setNx(key,value,expire);
        //占位成功
        if(status) {
            redisServiceImpl.expire(key, expire);
            return true;
        }
        return false;
    }

    /**
     *
     * @param key
     * @return
     */
    public boolean unLock(String key) {
        return redisServiceImpl.delKey(key);
    }
}
