package com.lxa.cl.controller;

import com.lxa.cl.pojo.Order;
import com.lxa.cl.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @RequestMapping("/add")
    public String add(){
        Order order=new Order();
        order.setName("zhangsan");
        order.setPrice(9000);
        int i = orderService.addOrder(order);
        return "添加成功----"+i;
    }
}
