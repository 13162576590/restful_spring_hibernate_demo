package com.icss.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TFLOW")
public class Flow implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int id;

    private String orderid;

    private String flowinfo;

    @Id
    @Column(name = "AUTOID", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getFlowinfo() {
        return flowinfo;
    }

    public void setFlowinfo(String flowinfo) {
        this.flowinfo = flowinfo;
    }

    @Override
    public String toString() {
        return "Flow [id=" + id + ", orderid=" + orderid + ", flowinfo=" + flowinfo + "]";
    }

}
