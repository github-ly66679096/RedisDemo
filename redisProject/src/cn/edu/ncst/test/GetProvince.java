package cn.edu.ncst.test;


import cn.edu.ncst.util.JedisUtil;
import redis.clients.jedis.Jedis;


/**
 * @author ：LIYUE
 * @date ：Created in 2020/10/25 18:23
 * @description：
 */
public class GetProvince {
    public static void main(String[] args) {

       /* JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);
        jedisPoolConfig.setMaxIdle(10);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);
        Jedis jedis = jedisPool.getResource();*/
        Jedis jedis = JedisUtil.getJedis();
      /*  jedis.lpush("user", "name", "age");
        List<String> list = jedis.lrange("user", 0, -1);
        for (String info : list) {
            System.out.println(info);
        }*/
        String json = "[{\"id\":1,\"code_pid\":110000,\"name\":\"河北省\"},{\"id\":2,\"code_pid\":120000,\"name\":\"广东省\"},{\"id\":3,\"code_pid\":130000,\"name\":\"湖南省\"}]";
        jedis.set("province", json);
        System.out.println(jedis.get("province"));
    }
}
