package cn.edu.ncst.dao;

import cn.edu.ncst.pojo.City;
import cn.edu.ncst.pojo.Province;

import java.util.List;

public interface ProvenceCityDao {
    /*
     * 获得省的数据
     * */

    public List<Province> getProvince();

    /**
     * 获得市的数据
     *
     * @param pId 省的id
     * @return
     */
    public List<City> getCity(String pId);
}
