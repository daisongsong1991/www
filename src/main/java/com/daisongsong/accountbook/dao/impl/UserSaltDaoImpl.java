package com.daisongsong.accountbook.dao.impl;

import com.daisongsong.accountbook.bean.UserSaltInfo;
import com.daisongsong.accountbook.dao.UserSaltDao;
import com.daisongsong.accountbook.dao.jdbc.JdbcManager;

import java.sql.*;

/**
 * Created by daisongsong on 16/7/29.
 */
public class UserSaltDaoImpl implements UserSaltDao {

    public int addUserSalt(UserSaltInfo info) {
        Connection connection = JdbcManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT user_salt(salt, user_id) VALUES (?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, info.getSalt());
            ps.setInt(2, info.getUserId());
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

    public UserSaltInfo findByUserId(int userId) {
        Connection connection = JdbcManager.getInstance().getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM user_salt WHERE user_id=?");
            ps.setInt(1, userId);
            ResultSet set = ps.executeQuery();
            if (set.next()) {
                UserSaltInfo info = new UserSaltInfo();
                info.setUserId(userId);
                info.setId(set.getInt("id"));
                info.setSalt(set.getString("salt"));
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
