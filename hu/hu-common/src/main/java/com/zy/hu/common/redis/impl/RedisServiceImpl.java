package com.zy.intelligentdevice.common.redis.impl;

import com.alibaba.fastjson.JSON;
import com.zy.intelligentdevice.common.redis.RedisService;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.cluster.api.async.RedisAdvancedClusterAsyncCommands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@Service("redisServiceImpl")
public class RedisServiceImpl implements RedisService {

    private static Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);

    @Resource
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public boolean set(final String key, final String value, final int expire) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.setEx(serializer.serialize(key),expire, serializer.serialize(value));
                return true;
            }
        });
        return result;
    }

    @Override
    public boolean set(final String key, final String value) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.set(serializer.serialize(key), serializer.serialize(value));
                return true;
            }
        });
        return result;
    }
    @Override
    public String get(final String key){
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value =  connection.get(serializer.serialize(key));
                return serializer.deserialize(value);
            }
        });
        return result;
    }

    @Override
    public long increment(final String key){
        long count =  redisTemplate.opsForValue().increment(key,1);
        return count;
    }

    public void setForValue(final String key,int timeout){
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    @Override
    public boolean expire(final String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    @Override
    public long lpush(final String key, Object obj) {
        final String value = JSON.toJSONString(obj);
        long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                long count = connection.lPush(serializer.serialize(key), serializer.serialize(value));
                return count;
            }
        });
        return result;
    }

    @Override
    public long rpush(final String key, Object obj) {
        final String value = JSON.toJSONString(obj);
        long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                long count = connection.rPush(serializer.serialize(key), serializer.serialize(value));
                return count;
            }
        });
        return result;
    }

    @Override
    public String lpop(final String key) {
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] res =  connection.lPop(serializer.serialize(key));
                return serializer.deserialize(res);
            }
        });
        return result;
    }

    /**
     * 删除
     * @param key 匹配的key
     * @return 删除成功的条数
     */
    @Override
    public Boolean delKey(final String key) {
       return   redisTemplate.delete(key);
    }

    @Override
    public boolean setNx(final String key,final Object value){
        return redisTemplate.opsForValue().setIfAbsent(key,value);
    }

    //获取超时时间
    @Override
    public long getExpire(final String key){
        return redisTemplate.getExpire(key);
    }

    //设置getset
    @Override
    public Object getSet(final String key,final Object value){
        return redisTemplate.opsForValue().getAndSet(key,value);
    }

    @Override
    public boolean setNx(String key, String value, int expiredTime) {
        Boolean resultBoolean = null;
        try {
            resultBoolean = redisTemplate.execute((RedisCallback<Boolean>) connection -> {
                Object nativeConnection = connection.getNativeConnection();
                String redisResult = "";
                @SuppressWarnings("unchecked")
                RedisSerializer<String> stringRedisSerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
                //lettuce连接包下序列化键值，否知无法用默认的ByteArrayCodec解析
                byte[] keyByte = stringRedisSerializer.serialize(key);
                byte[] valueByte = stringRedisSerializer.serialize(value);
                // lettuce连接包下 redis 单机模式setnx
                if (nativeConnection instanceof RedisAsyncCommands) {
                    RedisAsyncCommands commands = (RedisAsyncCommands)nativeConnection;
                    //同步方法执行、setnx禁止异步
                    redisResult = commands
                            .getStatefulConnection()
                            .sync()
                            .set(keyByte, valueByte, SetArgs.Builder.nx().ex(expiredTime));
                }
                // lettuce连接包下 redis 集群模式setnx
                if (nativeConnection instanceof RedisAdvancedClusterAsyncCommands) {
                    RedisAdvancedClusterAsyncCommands clusterAsyncCommands = (RedisAdvancedClusterAsyncCommands) nativeConnection;
                    redisResult = clusterAsyncCommands
                            .getStatefulConnection()
                            .sync()
                            .set(keyByte, keyByte, SetArgs.Builder.nx().ex(expiredTime));
                }
                //返回加锁结果
                return "OK".equalsIgnoreCase(redisResult);
            });
        } catch (Exception e) {
            logger.info("setNx-{}",e.getMessage());
        }
        return resultBoolean != null && resultBoolean;
    }

    /**
     * 删除
     * @param keys 匹配的key
     * @return 模糊删除
     */
    @Override
    public Long delKeys(final String keys) {
        Long lo = redisTemplate.delete(redisTemplate.keys(keys + "*"));
        return lo;
    }

}
