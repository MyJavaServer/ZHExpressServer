package com.zhihui.zhexpress.model;

public class UserToken {

    private int userId;       //用户id
    private String name;
    private int type;            //用户类型
    private String deviceType;   //设备种类  android/ios/pc
    private String deviceModel;  //设备型号

    public UserToken() {

    }


    public UserToken(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public UserToken(int userId, String name, int type) {
        this.userId = userId;
        this.name = name;
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }
}
