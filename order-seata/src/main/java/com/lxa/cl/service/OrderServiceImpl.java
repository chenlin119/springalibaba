package com.lxa.cl.service;

import com.lxa.cl.dao.OrderDao;
import com.lxa.cl.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private RestTemplate restTemplate;
    public int addOrder(Order order) {

        return orderDao.addOrder(order);
    }
}
