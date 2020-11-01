package cn.edu.ncst.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * describe
 * 作者：曾昭武
 */
public class DBUtil {

    /**
     * 1.在src下面 创建sbq.properties
     * properties这个不能随便取，这是文件后缀名
     * 在db. properties 中是键值 对的形式
     */


    private static String driverNameSbq;
    private static String urlSbq;
    private static String userNameSbq;
    private static String pwdSbq;

    //static 是在类加载之前执行的，执行几次请问?
    static {
        init();
    }

    //init 的只是一个方法，我自己创建的
    public static void init() {
        //要去把 db.properties 文件中的数据读取出来
        //1. 创建Properties 对象
        Properties prSbq = new Properties();

        //2.拿到db.properties 文件路径
        String pathSbq = "db.properties";

        //3.通过输入流读取db.properties  通过反射 ,直接这样写就可以了
        InputStream inputStream = DBUtil.class.getClassLoader().getResourceAsStream(pathSbq);

        //4.把 读到到的数据 加载到  Properties prSbq 对象中
        try {
            assert inputStream != null;
            prSbq.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //5.可以把 Properties中的数据拿出来，通过键值对的方法
        //各位同学，这里一定要测试，是否取到值了
        System.out.println(prSbq.get("driverName"));
        System.out.println(prSbq.get("url"));
        System.out.println(prSbq.get("userName"));
        System.out.println(prSbq.get("pwd"));

        //最后如何操作?
        driverNameSbq = (String) prSbq.get("driverName");
        urlSbq = (String) prSbq.get("url");
        userNameSbq = (String) prSbq.get("userName");
        pwdSbq = (String) prSbq.get("pwd");

        //一位合格开发者，都是会debug调试 ，会一段一段测试
        //千万不要把程序全写好了再去测试 如果你技术牛可以全写完都不用测试


    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            //1.加载驱动
            Class.forName(driverNameSbq);
            //2.建立连接
            conn = DriverManager.getConnection(urlSbq, userNameSbq, pwdSbq);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }

    /**
     * 封装释放资源
     */
    public static void closeAll(Connection conn, Statement stm, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (stm != null) {
                stm.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 封装DML
     *
     * @param sql
     * @param sbq
     * @return
     */
    public static int executeUpdate(String sql, Object[] sbq) {
        int num = 0;
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            //以后如果各位同学写代码，请务必这里调试， conn是否有值
            conn = DBUtil.getConnection();
            //3.处理预编译SQL语句  PreparedStatement  这里会使用到? 就是占位符  从左边开始数
            pstm = conn.prepareStatement(sql);
            //4.因为 sbq 是已经知道有多少条数据的
            for (int i = 0; i < sbq.length; i++) {
                pstm.setObject(i + 1, sbq[i]);
            }

            //5.执行预编译SQL语句  DML  使用 executeUpdate(请不要写任何语句)
            //这一句我先不执行
            num = pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //7.释放资源  这种是最标准的
            DBUtil.closeAll(conn, pstm, null);
        }

        return num;
    }

    public static void main(String[] args) {
        System.out.println(DBUtil.getConnection());
    }
}
