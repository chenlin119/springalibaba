package com.lxa.cl.dao;


import org.apache.ibatis.annotations.Update;

public interface StockDao {
    @Update("update stock set number=number-1 where id=#{id}")
    int updateStock(int id);
}
