<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/10/15
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" isErrorPage="false" %>
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script>

    $(function () {
        $.get("${pageContext.request.contextPath}/getProvince.do", function (data) {
            // alert(data);
            //var data = JSON.parse(data);
            //1.获取select
            var province = $("#province");
            document.getElementById("province").options.length = 0;
            //4.调用select的append追加option
            province.append("<option value='-1'>--请选择省份--</option>");
            //2.遍历json数组
            $(data).each(function () {
                //3.创建<option>
                var option = "<option value='" + this.code_pid + "'>" + this.name + "</option>";

                //4.调用select的append追加option
                province.append(option);
            });
        });
    });


    function getCity(value) {
        alert("输出了");
        alert(value);
        $.ajax({
            url: "${pageContext.request.contextPath}/getCity.do",
            data: "pId=" + value,
            success: function (data) {
                //alert(data);
                // var data = JSON.parse(datas);
                //1.获取select
                var city = $("#city");
                document.getElementById("city").options.length = 0;
                //2.遍历json数组
                $(data).each(function () {
                    //3.创建<option>
                    var option = "<option value='" + this.codeCid + "'>" + this.name + "</option>";

                    //4.调用select的append追加option
                    city.append(option);
                });
            }
        });
    }
</script>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<select id="province" onchange="getCity(this.value)">
    <option>--请选择省份--</option>

</select>

<select id="city">
    <option>--请选择市--</option>
</select>

</body>
</html>
