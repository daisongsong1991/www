package com.daisongsong.www.dao;

import com.daisongsong.www.bean.UserSaltInfo;

/**
 * Created by daisongsong on 16/7/29.
 */
public interface UserSaltDao {
    int addUserSalt(UserSaltInfo info);

    UserSaltInfo findByUserId(int userId);
}
