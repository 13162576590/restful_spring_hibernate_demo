package com.icss.service.dto;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.hibernate.dao.BaseDaoImpl;
import com.icss.model.OrderFlow;


@Repository
public class FlowDao<T> extends BaseDaoImpl<T> {



//    public List<OrderFlow> getFlowInfo(String orderid) throws Exception {
//        List<OrderFlow> flows = null;
//        String sql="select o.state,o.orderinfo,o.uname,o.orderid,f.flowinfo " +
//                   " from tflow f,torder o  where o.orderid = f.orderid and o.orderid=?";
//        this.openconnection();
//        PreparedStatement ps = this.conn.prepareStatement(sql);
//        ps.setString(1, orderid);
//        ResultSet rs = ps.executeQuery();
//        if(rs != null){
//            flows = new ArrayList<OrderFlow>();
//            while(rs.next()){
//                OrderFlow flow = new OrderFlow();
//                flow.setFlowinfo(rs.getString("flowinfo"));
//                flow.setOrderid(rs.getString("orderid"));
//                flow.setOrderinfo(rs.getString("orderinfo"));
//                flow.setUname(rs.getString("uname"));
//                flows.add(flow);
//            }
//        }
//        System.out.println(flows);
//        return flows;
//    }

    public List<OrderFlow> getFlowInfo(String orderid) throws Exception {
        List<OrderFlow> flows = null;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderid", orderid);

        String sql="select o.state,o.orderinfo,o.uname,o.orderid,f.flowinfo from tflow f,torder o  where o.orderid = f.orderid and o.orderid = ?";
        ResultSet rs = this.executeQuery(sql, params);

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


//        for (Object[] obj : list) {
//            System.out.println(obj);
//
//            OrderFlow orderFlow = new OrderFlow();
//            BeanUtils.copyProperties(obj, orderFlow);
//            if (flows == null) {
//                flows = new ArrayList<OrderFlow>();
//            }
//            flows.add(orderFlow);
//        }
//        this.openconnection();
//        PreparedStatement ps = this.conn.prepareStatement(sql);
//        ps.setString(1, orderid);
//        ResultSet rs = ps.executeQuery();
//        if(rs != null){
//            flows = new ArrayList<OrderFlow>();
//            while(rs.next()){
//                OrderFlow flow = new OrderFlow();
//                flow.setFlowinfo(rs.getString("flowinfo"));
//                flow.setOrderid(rs.getString("orderid"));
//                flow.setOrderinfo(rs.getString("orderinfo"));
//                flow.setUname(rs.getString("uname"));
//                flows.add(flow);
//            }
//        }
        System.out.println(flows);
        return flows;
    }

    public List<T> dumpResultSet(ResultSet rs, Class<T> clazz) throws Exception {
        List<T> list = new ArrayList<T>();
        ResultSetMetaData metaData = rs.getMetaData();
        int colCount = metaData.getColumnCount();

        Field[] fields = clazz.getDeclaredFields();

        while (rs.next()) {
            T newInstance = clazz.newInstance();

            for (int i = 1; i <= colCount; i++) {
                try {
                    Object value = rs.getObject(i);
                    for (int j = 0; j < fields.length; j++) {
                        Field f = fields[j];
                        if (f.getName().equalsIgnoreCase(metaData.getColumnName(i).replaceAll("_", ""))) {
                            BeanUtils.copyProperties(newInstance, f.getName(), (Class<?>) value);
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            list.add(newInstance);
        }

        return list;
    }

}
