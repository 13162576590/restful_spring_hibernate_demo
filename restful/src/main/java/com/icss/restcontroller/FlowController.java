package com.icss.restcontroller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.icss.model.OrderFlow;
import com.icss.service.IOrderFlowService;

@RestController
public class FlowController {

    @Resource
    IOrderFlowService service;

    @RequestMapping(value="/flowinfo/{orderid}")
    public List<OrderFlow> getFlowInfo(@PathVariable String orderid) {
        List<OrderFlow> flows = null;
        try {
            flows = service.getFlowInfo(orderid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flows;
    }

    @RequestMapping(value="/flowadd/{orderid}/{flowinfo}", method = RequestMethod.POST)
    public int addFlowInfo(@PathVariable String orderid, @PathVariable String flowinfo) {
        int result = 1;
        return result;
    }
}
