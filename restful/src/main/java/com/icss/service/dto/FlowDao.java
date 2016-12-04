package com.icss.service.dto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.icss.dao.impl.BaseDao;
import com.icss.model.OrderFlow;

@Repository
public class FlowDao<T> extends BaseDao {

    public List<OrderFlow> getFlowInfo(String orderid) throws Exception {
        List<OrderFlow> flows = null;
        String sql="select o.state,o.orderinfo,o.uname,o.orderid,f.flowinfo " +
                   " from tflow f,torder o  where o.orderid = f.orderid and o.orderid=?";
        this.openconnection();
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setString(1, orderid);
        ResultSet rs = ps.executeQuery();
        if(rs != null){
            flows = new ArrayList<OrderFlow>();
            while(rs.next()){
                OrderFlow flow = new OrderFlow();
                flow.setFlowinfo(rs.getString("flowinfo"));
                flow.setOrderid(rs.getString("orderid"));
                flow.setOrderinfo(rs.getString("orderinfo"));
                flow.setUname(rs.getString("uname"));
                flows.add(flow);
            }
        }
        System.out.println(flows);
        return flows;
    }


}
