package com.daisongsong.www.dao;

import com.daisongsong.www.bean.UserInfo;

import java.util.List;

/**
 * Created by daisongsong on 16/7/28.
 */
public interface UserDao {

    List<UserInfo> getAllUser();

    int insertUser(UserInfo info);

    boolean updateUser(UserInfo info);

    UserInfo getUseByName(String name);
}
