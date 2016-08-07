package com.daisongsong.www.filter;

import org.apache.catalina.servlet4preview.http.HttpFilter;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by daisongsong on 16/7/27.
 */
public class TimeFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        super.doFilter(request, response, chain);
        long end = System.currentTimeMillis();
        System.out.println(String.format(Locale.CHINA, "[%s] time spent : %d", request.getRequestURI(), (end - start)));
    }

    public void destroy() {

    }
}
