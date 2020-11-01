package cn.edu.ncst.dao;

import cn.edu.ncst.pojo.City;
import cn.edu.ncst.pojo.Province;
import cn.edu.ncst.util.DBUtil;

import java.sql.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：LIYUE
 * @date ：Created in 2020/10/25 18:07
 * @description：
 */
public class ProvenceCityDaoImpl implements ProvenceCityDao {
    @Override
    public List<Province> getProvince() {

        List<Province> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            //2.建立连接
            conn = DBUtil.getConnection();
            //3.处理预编译SQL语句  PreparedStatement  这里会使用到? 就是占位符  从左边开始数
            pstm = conn.prepareStatement("SELECT * FROM province");
            //4.如果有 第三步有使用到? 占位符，这里就必需给所有? 赋值   上面有多少个，必需全部赋值
            //5.执行预编译SQL语句
            rs = pstm.executeQuery();
            //6.循环；
            while (rs.next()) {
                Province province = new Province();
                province.setId(rs.getString(1));
                province.setCodePid(rs.getString(2));
                province.setName(rs.getString(3));

                //这里很多同学忘记了   把 province添加到 list集合中
                list.add(province);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return list;
    }

    @Override
    public List<City> getCity(String pId) {
        List<City> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            //2.建立连接
            conn = DBUtil.getConnection();
            //3.处理预编译SQL语句  PreparedStatement  这里会使用到? 就是占位符  从左边开始数
            pstm = conn.prepareStatement("SELECT * FROM city WHERE code_pid=?");
            //4.如果有 第三步有使用到? 占位符，这里就必需给所有? 赋值   上面有多少个，必需全部赋值
            pstm.setObject(1,pId);
            //5.执行预编译SQL语句
            rs = pstm.executeQuery();
            //6.循环；
            while (rs.next()) {
                City city = new City();
                city.setId(rs.getString(1));
                city.setCodeCid(rs.getString(2));
                city.setName(rs.getString(3));
                //这里很多同学忘记了   把 province添加到 list集合中
                list.add(city);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return list;
    }
}
