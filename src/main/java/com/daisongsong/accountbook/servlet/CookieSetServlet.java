package com.daisongsong.accountbook.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * Created by daisongsong on 16/8/6.
 */
public class CookieSetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = new Cookie[6];
        cookies[0] = new Cookie("time", String.valueOf(System.currentTimeMillis()));
        cookies[1] = new Cookie("q", "qqqqqq");
        cookies[2] = new Cookie("pathonly", "/use-cookie");
        cookies[2].setPath("/use-cookie");

        cookies[3] = new Cookie("allpath", "/");
        cookies[3].setPath("/");

        cookies[4] = new Cookie("pathonly1", "/use_cookie");
        cookies[4].setPath("/use_cookie");

        cookies[5] = new Cookie("age", String.valueOf(30));
        cookies[5].setMaxAge(30);

        cookies[3].setValue("");

        StringBuffer sb = new StringBuffer();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                sb.append(String.format("cookie[%d]=[%s=%s,maxAge=%d,path=%s]\n",
                        i, cookie.getName(), cookie.getValue(), cookie.getMaxAge(), cookie.getPath()));
                resp.addCookie(cookie);
            }
        }
        resp.getWriter().print(sb.toString());
        resp.getWriter().flush();
    }
}
