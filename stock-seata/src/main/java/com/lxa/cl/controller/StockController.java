package com.lxa.cl.controller;

import com.lxa.cl.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private StockService stockService;

    @RequestMapping("/delete")
    public String delete(){
        stockService.updateStock(1);
        return "库存扣减成功----";
    }
}
