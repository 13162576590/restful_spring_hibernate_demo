package com.icss.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.icss.model.OrderFlow;
import com.icss.service.dto.FlowDao;

@Service
public class OrderFlowServiceImpl implements IOrderFlowService {

    @Resource
    FlowDao<OrderFlow> dao;

    @Override
    public List<OrderFlow> getFlowInfo(String orderid) throws Exception {
        List<OrderFlow> flows = null;
        try {
            flows = dao.getFlowInfo(orderid);
        } catch (Exception e) {
            throw e;
        }
        return flows;
    }

}
