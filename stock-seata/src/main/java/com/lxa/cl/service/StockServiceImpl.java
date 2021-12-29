package com.lxa.cl.service;

import com.lxa.cl.dao.StockDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService{
    @Autowired
    private StockDao stockDao;
    public int updateStock(int id) {
        return stockDao.updateStock(id);
    }
}
