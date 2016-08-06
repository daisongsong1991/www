package com.daisongsong.accountbook.servlet;

import com.daisongsong.accountbook.bean.UserInfo;
import com.daisongsong.accountbook.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by daisongsong on 16/8/1.
 */
public class UserServlet extends HttpServlet{
    private static final String PATH_REGISTER = "register";
    private static final String PATH_LOGIN = "login";

    private UserService mUserService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().contains(PATH_REGISTER)) {
            doRegister(req, resp);
        }else if(req.getRequestURI().contains(PATH_LOGIN)){
            doLogin(req, resp);
        }else {

        }
    }

    private void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        //检查是否已经登录过了
        UserInfo info = (UserInfo) req.getSession().getAttribute("userInfo");
        if(info == null || !info.getName().equals(name)){
            req.getSession().removeAttribute("userInfo");
            info = mUserService.login(name, password);
        }

        if (info != null) {
            req.getSession().setAttribute("userInfo", info);
            req.setAttribute("userInfo", info);
            req.setAttribute("lastLoginTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss S").format(new Date(info.getLastLoginTime())));
            req.getRequestDispatcher("../main_page.jsp").forward(req, resp);
        } else {
            req.setAttribute("msg", "登录失败!");
            req.getRequestDispatcher("../message.jsp").forward(req, resp);
        }
    }

    private void doRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserInfo resultInfo = null;

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String passwordRepeat = req.getParameter("password_repeat");
        if(name != null && name.length() >=6 && name.length() <=20
                && password != null && password.length() >=6 && password.length() <=20
                && password.equals(passwordRepeat)) {
            UserInfo info = new UserInfo();
            info.setName(name);
            info.setPassword(password);
            info.setCreateTime(System.currentTimeMillis());
            resultInfo = mUserService.registerNewUser(info);
        }

        req.setAttribute("msg", resultInfo == null ? "注册失败!" : "注册成功!");
        req.getRequestDispatcher("../message.jsp").forward(req, resp);
    }
}
