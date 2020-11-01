package cn.edu.ncst.util;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author ：LIYUE
 * @date ：Created in 2020/10/25 15:03
 * @description： JedisUtil工具类
 * <p>
 * 1. 首先创建一个静态方法用来产生Jedis对象
 * 2. 由于使用JedisPool连接池，所以要声明一个JedisPool对象
 * 3. 连接池对象中的参数不是在java代码中直接手动输入，而是通过properties文件中的数据
 * 所以还要写一个静态方法快用来读取properties中的数据，并最终产生一个JedisPool对象----过程中有一个转化错误
 * 4. new RedisPool
 */
public class JedisUtil {

    //创建一个连接池对象
    private static JedisPool jedisPool;

    //静态初始化块，用来读取properties中的数据，并最终产生一个JedisPool对象
    static {
        //1.读取配置文件
        InputStream is = JedisUtil.class.getClassLoader().getResourceAsStream("redis.properties");

        //2.创建Properties对象
        Properties properties = new Properties();

        //3. 加载
        try {
            assert is != null;
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 这个地方有一个强制类型转换，否则包一个java.lang.Object  不能转换java.lang.String
         */
        String info = (String) properties.get("host");
        String port1 = (String) properties.get("port");
        String maxTotal1 = (String) properties.get("maxTotal");
        String maxIdle1 = (String) properties.get("maxIdle");


        int port = Integer.parseInt(port1);
        int maxTotal = Integer.parseInt(maxTotal1);
        int maxIdle = Integer.parseInt(maxIdle1);
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(maxIdle);
        config.setMaxTotal(maxTotal);

        jedisPool = new JedisPool(config, info, port);

    }

    public static Jedis getJedis() {
        return  jedisPool.getResource();
    }
}
