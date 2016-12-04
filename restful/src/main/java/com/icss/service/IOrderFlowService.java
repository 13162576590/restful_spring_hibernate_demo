package com.icss.service;

import java.util.List;

import com.icss.model.OrderFlow;

public interface IOrderFlowService {

    public List<OrderFlow> getFlowInfo(String orderid) throws Exception;

}
