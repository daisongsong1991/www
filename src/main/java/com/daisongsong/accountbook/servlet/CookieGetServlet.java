package com.daisongsong.accountbook.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by daisongsong on 16/8/6.
 */
public class CookieGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();

        StringBuffer sb = new StringBuffer();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                sb.append(String.format("cookie[%d]=[%s=%s]\n", i, cookie.getName(), cookie.getValue()));
            }
        }

        resp.getWriter().print(sb.toString());
        resp.getWriter().flush();
    }


}
