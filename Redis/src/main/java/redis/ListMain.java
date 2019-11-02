package redis;

import redis.clients.jedis.Jedis;
import utils.RedisUtils;

/**
 * <h3>Redis</h3>
 * <p>Redis的List</p>
 *
 * @author : gwh
 * @date : 2019-10-28 16:34
 **/
public class ListMain {
    public static void main(String[] args) {
        Jedis jedis = RedisUtils.getJedis();
        jedis.lpush("tian","乔峰", "段誉", "虚竹", "鸠摩智");
        for (String name:jedis.lrange("tian", 0,-1 )) {
            System.out.println(name);
        }
        jedis.del("柜台1");

        jedis.close();
    }

}
