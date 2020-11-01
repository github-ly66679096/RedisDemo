package cn.edu.ncst.service;


import cn.edu.ncst.pojo.City;
import cn.edu.ncst.pojo.Province;

import java.util.List;

public interface ProvinceCityService {

    public List<Province> getProvince();
    public List<City> getCity(String pId);

    /*
     * 获得省的数据
     * */

    public String getProvinceJson();

    /**
     * 获得市的数据
     *
     * @param pId 省的id
     * @return
     */

    /*
     *使用redis缓存数据，如果缓存中有数据的话直接使用缓存中的数据，如果缓存中
     * 没有数据  首先使用mysql中的数据  然后将数据存储到redis中一份  然后将数据返回
     *
     * 当第二次或更多次访问数据时候直接使用redis中的数据了。
     * */
    public String getCityJson(String pId);
}
