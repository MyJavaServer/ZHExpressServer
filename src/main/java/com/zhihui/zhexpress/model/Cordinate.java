package com.zhihui.zhexpress.model;

import java.util.Date;

public class Cordinate {
    private Integer id;

    private String xlist;

    private String ylist;

    private Date createtime;

    private Date updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getXlist() {
        return xlist;
    }

    public void setXlist(String xlist) {
        this.xlist = xlist == null ? null : xlist.trim();
    }

    public String getYlist() {
        return ylist;
    }

    public void setYlist(String ylist) {
        this.ylist = ylist == null ? null : ylist.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}