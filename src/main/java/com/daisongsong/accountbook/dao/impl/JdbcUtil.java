package com.daisongsong.accountbook.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by daisongsong on 16/7/28.
 */
public class JdbcUtil {

    public static void close(Statement s, Connection c){
        if(s != null){
            try {
                s.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(c != null){
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
