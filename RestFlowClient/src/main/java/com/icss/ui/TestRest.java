package com.icss.ui;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.icss.service.dto.OrderFlow;

public class TestRest {
	
    public static void testAddFlows() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> map = new HashMap<String, String>();
        map.put("orderid", "2015100112345");
        map.put("flowinfo", "haerbin arrived");

        Integer result = restTemplate.postForObject("http://localhost:8080/restful/flowadd/{orderid}/{flowinfo}", null, Integer.class, map);
        if (result == 1) {
        	System.out.println("订单信息添加成功");
        } else {
        	System.out.println("订单信息添加失败");
        }
    }

    public static void testGetFlows() {
        RestTemplate restTemplate = new RestTemplate();
        OrderFlow[] flows = restTemplate.getForObject("http://localhost:8080/restful/flowinfo/2015100112345", OrderFlow[].class);
        for (OrderFlow flow : flows) {
            System.out.println(flow.getOrderid() + "--" + flow.getFlowinfo());
        }
    }

    public static void main(String[] args) {
    	testAddFlows();
//        testGetFlows();
    }
}
