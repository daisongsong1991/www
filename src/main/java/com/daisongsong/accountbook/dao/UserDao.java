package com.daisongsong.accountbook.dao;

import com.daisongsong.accountbook.bean.UserInfo;

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
