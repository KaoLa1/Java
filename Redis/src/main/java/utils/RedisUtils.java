package utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * <h3>Redis</h3>
 * <p>Redis工具类</p>
 *
 * @author : gwh
 * @date : 2019-10-28 15:16
 **/
public class RedisUtils {
    private final static String host = "localhost";
    private static JedisPool jedisPool;
    private final static int port = 6379;

    public static synchronized Jedis getJedis() {
        if (jedisPool == null) {
            //创建连接池
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            //设置最大空闲数
            jedisPoolConfig.setMaxIdle(10);
            //设置最大连接数
            jedisPoolConfig.setMaxTotal(100);
            //设置最长等待时间
            jedisPoolConfig.setMaxWaitMillis(1000);
            //对连接进行有效性检查
            jedisPoolConfig.setTestOnBorrow(true);
            jedisPool = new JedisPool(jedisPoolConfig, host, port);
        }
        return jedisPool.getResource();
    }
}
