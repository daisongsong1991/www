package com.daisongsong.www.dao.impl;

import com.daisongsong.www.bean.UserInfo;
import com.daisongsong.www.dao.UserDao;
import com.daisongsong.www.dao.jdbc.JdbcManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daisongsong on 16/7/28.
 */
public class UserDaoImpl implements UserDao {

    public List<UserInfo> getAllUser() {
        Connection connection = JdbcManager.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM user");

            List<UserInfo> userInfos = new ArrayList<UserInfo>();
            while (set.next()) {
                UserInfo info = new UserInfo();
                info.setId(set.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insertUser(UserInfo info) {
        Connection connection = JdbcManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT user(name, password, create_time, last_login_time) VALUES (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, info.getName());
            ps.setString(2, info.getPassword());
            ps.setLong(3, info.getCreateTime());
            ps.setLong(4, info.getLastLoginTime());
            ps.executeUpdate();
            ResultSet set = ps.getGeneratedKeys();
            if (set.next()) {
                return set.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(ps, connection);
        }
        return -1;
    }

    public boolean updateUser(UserInfo info) {
        Connection connection = JdbcManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("UPDATE user SET name=? , password=? , last_login_time=? WHERE id=?");
            ps.setString(1, info.getName());
            ps.setString(2, info.getPassword());
            ps.setLong(3, info.getLastLoginTime());
            ps.setInt(4, info.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(ps, connection);
        }
        return false;
    }

    public UserInfo getUseByName(String name) {
        Connection connection = JdbcManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM user WHERE name=?");
            ps.setString(1, name);
            ResultSet set = ps.executeQuery();
            if (set.next()) {
                UserInfo info = new UserInfo();
                info.setId(set.getInt("id"));
                info.setName(name);
                info.setCreateTime(set.getLong("create_time"));
                info.setLastLoginTime(set.getLong("last_login_time"));
                info.setPassword(set.getString("password"));
                return info;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(ps, connection);
        }
        return null;
    }


}
