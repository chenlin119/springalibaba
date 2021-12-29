package com.lxa.cl.stock.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {
    @RequestMapping("/info")
    public String stockInfo(){
        int i=1/0;
        return "=====stockInfo====";
    }
}
