package com.bosssoft.itfinance.epay.v2.cashierdesk.common.util;

import redis.clients.jedis.Jedis;

public class JedisUtil {
    private static JedisPoolUtil jedisPoolUtil = SpringUtils.getBean("jedisPoolUtil");
    /**
     * 设置有效期的字符串缓存
     * 
     * @param key
     * @param value
     * @param seconds
     */
    public static void set(String key, String value, int seconds) {
        /**
         * JDK 7新特性写法，实现了AutoCloseable接口，这个写法会自动调用close方法关闭
         */
        try (Jedis jedis = jedisPoolUtil.getJedis()) {
            jedis.setex(key, seconds, value);
        }
    }
    /**
     * 设置字符串缓存
     * 
     * @param key
     * @param value
     */
    public static void set(String key, String value) {
        try(Jedis jedis = jedisPoolUtil.getJedis()) {
            jedis.set(key, value);
        }
    }
    /**
     * 设置byte[]缓存
     * 
     * @param key
     * @param value
     */
    public static void setByte(byte[] key, byte[] value,int expire) {
        try(Jedis jedis = jedisPoolUtil.getJedis()) {
            jedis.set(key, value);
            expireBytesKey(key, expire);
        }
    }
    /**
     * 设置有效期的字符串缓存
     * 
     * @param key
     * @param value
     * @param seconds
     */
    public static void setex(String key, int seconds, String value) {
        try (Jedis jedis = jedisPoolUtil.getJedis()) {
            jedis.setex(key, seconds, value);
        }
    }
    /**
     * 获取字符串数据
     * 
     * @param key
     * @return
     */
    public static String get(String key) {
        try(Jedis jedis = jedisPoolUtil.getJedis()) {
            return jedis.get(key);
        }
    }
    /**
     * 获取byte[]数据
     * 
     * @param key
     * @return
     */
    public static byte[] getByte(byte[] key) {
        try(Jedis jedis = jedisPoolUtil.getJedis()) {
            return jedis.get(key);
        }
    }
    /**
     * 往列表头部插入数据
     * 
     * @param key
     * @param value
     */
    public static void lpush(String key, String value) {
        try(Jedis jedis = jedisPoolUtil.getJedis()) {
            jedis.lpush(key, value);
        }
    }
    /**
     * 往列表尾部插入数据
     * 
     * @param key
     * @param value
     */
    public static void rpush(String key, String value) {
        try(Jedis jedis = jedisPoolUtil.getJedis()) {
            jedis.rpush(key, value);
        }
    }
    /**
     * 从列表头部获取数据
     * 
     * @param key
     * @return
     */
    public static String lpop(String key) {
        try(Jedis jedis = jedisPoolUtil.getJedis()) {
          return jedis.lpop(key);
        }
    }
    /**
     * 从列表尾部获取数据
     * 
     * @param key
     * @return
     */
    public static String rpop(String key) {
        try(Jedis jedis = jedisPoolUtil.getJedis()) {
            return jedis.rpop(key);
        } 
    }
    /**
     * 获取列表长度
     * 
     * @param key
     * @return 列表长度，若返回-1则表示取值发生异常
     */
    public static long llen(String key) {
        try(Jedis jedis = jedisPoolUtil.getJedis()) {
            return jedis.llen(key);
        }
    }
    /**
     * 计数器递增
     * @param key
     * @return 执行 incr 命令之后 key 的值
     */
    public static long incr(String key) {
        try(Jedis jedis = jedisPoolUtil.getJedis()) {
            return jedis.incr(key);
        }
    }
    /**
     * 计数器递减
     * @param key
     * @return 执行 decr 命令之后 key 的值
     */
    public static long decr(String key) {
        try(Jedis jedis = jedisPoolUtil.getJedis()) {
            return jedis.decr(key);
        }
    }
    /**
     * 删除键
     * @param key
     * @return 执行 decr 命令之后 key 的值
     */
    public static long delByte(byte[] key) {
        try(Jedis jedis = jedisPoolUtil.getJedis()) {
            return jedis.del(key);
        }
    }
    /**
     * 设置key有效期
     * @param key
     * @param seconds
     */
    public static void expire(String key,int seconds) {
        try(Jedis jedis = jedisPoolUtil.getJedis()) {
            jedis.expire(key, seconds);
        }
    }
    public static void expireBytesKey(byte[] key, int expire){
    	 try(Jedis jedis = jedisPoolUtil.getJedis()) {
    		 jedis.expire(key, expire);
    	 }
    }
    /**
     * 判断member是否是集合key的成员
     * @param key
     * @param member
     * @return
     */
    public static boolean sismember(String key, String member) {
        try(Jedis jedis = jedisPoolUtil.getJedis()) {
            return jedis.sismember(key, member);
        }
    }
    /**
     * 往集合SET添加数据
     * @param key
     * @param member
     */
    public static void sadd(String key,String member) {
        try(Jedis jedis = jedisPoolUtil.getJedis()) {
            jedis.sadd(key, member);
        }
    }
    
    /**
     * 获取键的剩余有效秒数
     * 当 key 不存在时，返回 -2 。 当 key 存在但没有设置剩余生存时间时，返回 -1 。 否则，以毫秒为单位，返回 key 的剩余生存时间
     * 注意：在 Redis 2.8 以前，当 key 不存在，或者 key 没有设置剩余生存时间时，命令都返回 -1
     * @param key
     * @param member
     */
    public static Long pttl(String key) {
        try(Jedis jedis = jedisPoolUtil.getJedis()) {
            return jedis.pttl(key);
        }
    }
    
    /**
     * 删除键
     * @param key
     * @param value
     */
    public static void del(String key) {
        try(Jedis jedis = jedisPoolUtil.getJedis()) {
            jedis.del(key);
        }
    }
}
