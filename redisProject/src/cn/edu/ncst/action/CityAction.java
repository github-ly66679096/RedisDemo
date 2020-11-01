package cn.edu.ncst.action;

import cn.edu.ncst.service.ProvinceCityService;
import cn.edu.ncst.service.ProvinceCityServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * @author ：LIYUE
 * @date ：Created in 2020/10/25 18:38
 * @description：
 */
@WebServlet("/getCity.do")
public class CityAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决乱码的问题
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=utf-8");

        PrintWriter pw = resp.getWriter();

        //0. 当查询城市的数据的时候要使用一个参数
        String pId = req.getParameter("pId");

        //1.创建业务层
        ProvinceCityService ps = new ProvinceCityServiceImpl();

        //2.调用业务层
        /*--==========没有使用redis的时候=============*/

/*        List<Province> list = ps.getProvince();
        //3. 把数据转换成json格式

        JSONArray jsonArray = JSONArray.fromObject(list);
        System.out.println(jsonArray);
        //4.将数据显示到前端
        pw.print(jsonArray);*/

        /*============使用redis之后  主要是看service中的代码的变化===============*/
        String city_json = ps.getCityJson(pId);
        pw.write(city_json);
        System.out.println("ceshi sh");
    }
}
