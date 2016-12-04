package com.icss.service.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderFlow implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String orderid;

    private String orderinfo;

    private String username;

    private int state;

    private String flowinfo;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getOrderinfo() {
        return orderinfo;
    }

    public void setOrderinfo(String orderinfo) {
        this.orderinfo = orderinfo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getFlowinfo() {
        return flowinfo;
    }

    public void setFlowinfo(String flowinfo) {
        this.flowinfo = flowinfo;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "OrderFlow [orderid=" + orderid + ", orderinfo=" + orderinfo + ", username=" + username + ", state="
                + state + ", flowinfo=" + flowinfo + "]";
    }

}
