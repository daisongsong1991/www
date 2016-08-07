package com.daisongsong.www.bean;

/**
 * Created by daisongsong on 16/7/29.
 */
public class UserSaltInfo {
    private int id;
    private int userId;
    private String salt;

    public UserSaltInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "UserSaltInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", salt='" + salt + '\'' +
                '}';
    }
}
