package com.daisongsong.accountbook.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by daisongsong on 16/8/6.
 */
public class CookieGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        printCookies(req, resp);

        printHeaders(resp.getWriter(), req);
        printParams(resp.getWriter(), req);
        resp.getWriter().flush();
    }

    private void printParams(PrintWriter writer, HttpServletRequest req) {
        writer.print("\nPARAMS\n");
        StringBuffer sb = new StringBuffer();
        Map<String, String[]> map = req.getParameterMap();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            String[] value = entry.getValue();
            if (value != null) {
                for (String s : value) {
                    sb.append(String.format("%s=[%s]\n", entry.getKey(), s));
                }
            }
        }
        System.out.println(sb.toString());
        writer.print(sb.toString());
    }

    private void printCookies(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write("\nCOOKIES\n");
        Cookie[] cookies = req.getCookies();
        StringBuffer sb = new StringBuffer();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                sb.append(String.format("cookie[%d]=[%s=%s]\n", i, cookie.getName(), cookie.getValue()));
            }
        }
        resp.getWriter().print(sb.toString());
    }

    private void printHeaders(PrintWriter writer, HttpServletRequest req) {
        writer.write("\nHEADERS\n");
        StringBuilder sb = new StringBuilder();
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames != null && headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            Enumeration<String> headers = req.getHeaders(name);
            while (headers != null && headers.hasMoreElements()) {
                String value = headers.nextElement();
                sb.append(String.format("%s=[%s]\n", name, value));
            }
        }
        writer.write(sb.toString());
    }


}
