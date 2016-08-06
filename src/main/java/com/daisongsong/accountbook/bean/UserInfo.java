package com.daisongsong.accountbook.bean;

/**
 * Created by daisongsong on 16/7/28.
 */
public class UserInfo {
    private int id;
    private String name;
    private String password;
    private long createTime;
    private long lastLoginTime;

    public UserInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }

    public static UserInfo copyFrom(UserInfo userInfo) {
        UserInfo res = new UserInfo();
        res.id = userInfo.id;
        res.name = userInfo.name;
        res.password = userInfo.password;
        res.lastLoginTime = userInfo.lastLoginTime;
        res.createTime = userInfo.createTime;
        return res;
    }
}
