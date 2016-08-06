package com.daisongsong.accountbook.dao.jdbc;

import com.daisongsong.accountbook.dao.impl.JdbcUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by daisongsong on 16/7/28.
 */
public class JdbcManager {
    private String URL = "jdbc:mysql://192.168.31.156:3306/www";

    private static JdbcManager sInstance;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private JdbcManager(){

    }

    public static JdbcManager getInstance(){
        if(sInstance == null){
            synchronized (JdbcManager.class){
                if(sInstance == null){
                    sInstance = new JdbcManager();
                }
            }
        }
        return sInstance;
    }

    public Connection getConnection(){
        try {
            return DriverManager.getConnection(URL, "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args){
        JdbcManager instance = JdbcManager.getInstance();
        Connection connection = instance.getConnection();
        System.out.println(connection);
        JdbcUtil.close(null, connection);
    }
}
