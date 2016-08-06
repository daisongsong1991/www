package com.daisongsong.accountbook.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by daisongsong on 16/7/27.
 */
public class HelloServlet extends HttpServlet {
    private SimpleDateFormat mSimpleDateFormat;

    public HelloServlet() {
        mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss S");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("currentTime", mSimpleDateFormat.format(new Date()));
        req.setAttribute("ip", req.getLocalName());
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
