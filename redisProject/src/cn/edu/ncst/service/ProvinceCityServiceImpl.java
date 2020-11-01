package cn.edu.ncst.service;

import cn.edu.ncst.dao.ProvenceCityDao;
import cn.edu.ncst.dao.ProvenceCityDaoImpl;
import cn.edu.ncst.pojo.City;
import cn.edu.ncst.pojo.Province;
import cn.edu.ncst.util.JedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;


import java.util.List;

/**
 * @author ：LIYUE
 * @date ：Created in 2020/10/25 18:36
 * @description：
 */
public class ProvinceCityServiceImpl implements ProvinceCityService {
    private ProvenceCityDao pc = new ProvenceCityDaoImpl();


    public List<Province> getProvince() {
        return pc.getProvince();
    }

    public List<City> getCity(String pId) {
        return pc.getCity(pId);
    }

    @Override
    public String getProvinceJson() {

        String province_json = "";

        //1.先从redis中查询数据
        // 1.1 获取redis客户端连接
        Jedis jedis = JedisUtil.getJedis();

        //1.2 从redis中获得缓存数据
        province_json = jedis.get("province");

        //1.3 对获得的值进行判断，如果不为null的时候才 返回
        if (province_json == null || province_json.length() < 1) {

            //2.1 缓存中没有数据---那就按照原来的方式滴啊用dao层 执行相关操作
            List<Province> list = pc.getProvince();

            //2.2 然后将获得的数据序列化成json格式
            ObjectMapper mapper = new ObjectMapper();
            try {
                province_json = mapper.writeValueAsString(list);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            //2.3 将上面的json数据存储到redis中
            jedis.set("province", province_json);

            //2.4 关闭连接
        } else {
            //缓存中有数据---直接使用就行了

        }
        return province_json;
    }

    @Override
    public String getCityJson(String pId) {

        String city_json = "";

        //1.先从redis中查询数据
        // 1.1 获取redis客户端连接
        Jedis jedis = JedisUtil.getJedis();

        //1.2 从redis中获得缓存数据
        city_json = jedis.get("city" + pId);

        //1.3 对获得的值进行判断，如果不为null的时候才 返回
        if (city_json == null || city_json.length() < 1) {

            //2.1 缓存中没有数据---那就按照原来的方式滴啊用dao层 执行相关操作
            List<City> list = pc.getCity(pId);

            //2.2 然后将获得的数据序列化成json格式
            ObjectMapper mapper = new ObjectMapper();
            try {
                city_json = mapper.writeValueAsString(list);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            //2.3 将上面的json数据存储到redis中
            jedis.set("city" + pId, city_json);

            //3. 关闭连接
        } else {
            //缓存中有数据---直接使用就行了
        }
        return city_json;
    }
}
