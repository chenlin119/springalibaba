package com.lxa.cl.dao;

import com.lxa.cl.pojo.Order;
import org.apache.ibatis.annotations.Insert;

public interface OrderDao {
    @Insert("insert into order(name,price) values(#{name},#{price})")
    int addOrder(Order order);
}
