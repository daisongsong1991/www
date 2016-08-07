package com.daisongsong.www.service;

import com.daisongsong.www.bean.UserInfo;
import com.daisongsong.www.bean.UserSaltInfo;
import com.daisongsong.www.dao.UserDao;
import com.daisongsong.www.dao.UserSaltDao;
import com.daisongsong.www.dao.impl.UserDaoImpl;
import com.daisongsong.www.dao.impl.UserSaltDaoImpl;
import com.daisongsong.www.util.MD5Util;

import java.util.Locale;
import java.util.Random;

/**
 * Created by daisongsong on 16/7/29.
 */
public class UserService {
    private UserDao mUserDao = new UserDaoImpl();
    private UserSaltDao mUserSaltDao = new UserSaltDaoImpl();

    public static void main(String[] args) {
//        testRegister();
        testLogin();
    }

    private static void testLogin() {
        System.out.println(new UserService().login("test2", "123456"));
    }

    private static void testRegister() {
        UserInfo info = new UserInfo();
        info.setCreateTime(System.currentTimeMillis());
        info.setLastLoginTime(info.getCreateTime());
        info.setName("test3");
        info.setPassword("123456");

        UserInfo info1 = new UserService().registerNewUser(info);
        System.out.println("UserService.main " + info1);
    }

    public UserInfo registerNewUser(UserInfo info) {
        UserSaltInfo saltInfo = new UserSaltInfo();
        saltInfo.setSalt(generateSalt());

        info.setPassword(generateSaltedPassword(info.getPassword(), saltInfo.getSalt()));
        int userId = mUserDao.insertUser(info);
        if (userId <= 0) {
            return null;
        }

        saltInfo.setUserId(userId);
        if (mUserSaltDao.addUserSalt(saltInfo) <= 0) {
            return null;
        }


        return info;
    }

    private String generateSalt() {
        return MD5Util.MD5(String.format(Locale.CHINA, "%d_%d_%d", System.currentTimeMillis(), new Random().nextLong(), new Random().nextLong()));
    }

    public UserInfo login(String name, String password) {
        UserInfo userInfo = mUserDao.getUseByName(name);
        if (userInfo == null) {
            return null;
        }

        UserSaltInfo saltInfo = mUserSaltDao.findByUserId(userInfo.getId());
        if (saltInfo == null) {
            return null;
        }

        if (!generateSaltedPassword(password, saltInfo.getSalt()).equals(userInfo.getPassword())) {
            return null;
        } else {
            UserInfo info = UserInfo.copyFrom(userInfo);
            info.setLastLoginTime(System.currentTimeMillis());
            mUserDao.updateUser(info);
            return userInfo;
        }
    }

    public UserInfo getUserInfo(String name) {
        UserInfo userInfo = mUserDao.getUseByName(name);
        return userInfo;
    }

    private String generateSaltedPassword(String password, String salt) {
        return MD5Util.MD5(MD5Util.MD5(password) + MD5Util.MD5(salt));
    }
}
