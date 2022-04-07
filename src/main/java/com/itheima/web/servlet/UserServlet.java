package com.itheima.web.servlet;


import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
    private BrandService brandService = new BrandServiceImpl();
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //调用service查询
        List<Brand> brands = brandService.selectAll();

        //转为JSON
        String jsonString = JSON.toJSONString(brands);
        //写数据回去
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收数据（get）
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串


        //转为brand对象
        Brand brand = JSON.parseObject(params,Brand.class);

        //调用service添加剂
        brandService.add(brand);

        //响应一个成功的标识
        response.getWriter().write("success");

    }
}
