package com.zhihui.zhexpress.model;

public class UserInfo {
    /**
     * id : 1
     * num : 3098523
     * phone :
     * name : yin
     * nickname : null
     * type : 1
     * accessToken :
     */

    private int id;
    private String num;
    private String phone;
    private String name;
    private Object nickname;
    private int type;
    private String accessToken;
    private String result;

    public UserInfo(int id, String num, String phone, String name, Object nickname, int type, String accessToken) {
        this.id = id;
        this.num = num;
        this.phone = phone;
        this.name = name;
        this.nickname = nickname;
        this.type = type;
        this.accessToken = accessToken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getNickname() {
        return nickname;
    }

    public void setNickname(Object nickname) {
        this.nickname = nickname;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
