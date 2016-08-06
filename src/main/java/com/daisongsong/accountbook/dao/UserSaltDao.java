package com.daisongsong.accountbook.dao;

import com.daisongsong.accountbook.bean.UserSaltInfo;

/**
 * Created by daisongsong on 16/7/29.
 */
public interface UserSaltDao {
    int addUserSalt(UserSaltInfo info);

    UserSaltInfo findByUserId(int userId);
}
