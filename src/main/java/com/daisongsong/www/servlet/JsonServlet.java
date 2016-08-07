package com.daisongsong.www.servlet;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by daisongsong on 16/8/7.
 */
public class JsonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Data data = new Data();
        data.cookies = req.getCookies();
        data.params = new HashMap<String, String[]>();
        for (Map.Entry<String, String[]> entry : req.getParameterMap().entrySet()) {
            data.params.put(entry.getKey(), entry.getValue());
        }

        data.header = new HashMap<String, String[]>();
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            Enumeration<String> headers = req.getHeaders(name);
            if (headers != null) {
                List<String> values = new ArrayList<String>();
                while (headers.hasMoreElements()) {
                    values.add(headers.nextElement());
                }
                data.header.put(name, values.toArray(new String[values.size()]));
            }
        }

        JSONObject object = new JSONObject();
        object.put("data", data);
        object.put("resultCode", 123);

        String s = object.toJSONString();
        System.out.println("----------JsonServlet----------");
        System.out.println(s);
        resp.getWriter().write(s);
        resp.getWriter().flush();
    }

    public static class Data {
        public Cookie[] cookies;
        public Map<String, String[]> params;
        public Map<String, String[]> header;
    }
}
