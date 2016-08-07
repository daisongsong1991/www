package com.daisongsong.www.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by daisongsong on 16/8/7.
 */
public class MultiPartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        printCookies(req, resp);

        printHeaders(resp.getWriter(), req);
        printParams(resp.getWriter(), req);
        resp.getWriter().flush();
    }

    private void printParams(PrintWriter writer, HttpServletRequest req) {
        writer.print("\nPARAMS\n");

        StringBuffer sb = new StringBuffer();

        try {
            Collection<Part> parts = req.getParts();
            for (Part part : parts) {
                byte[] data = readFromStream(part.getInputStream());
                System.out.println("MultiPartServlet.printParams " + part.getName() + "=" + new String(data));
                sb.append(String.format("multipart: %s=[%s]\n", part.getName(), new String(data)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    private byte[] readFromStream(InputStream inputStream) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        try {
            int size = inputStream.read(buffer);
            while (size > 0) {
                baos.write(buffer, 0, size);
                size = inputStream.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
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
