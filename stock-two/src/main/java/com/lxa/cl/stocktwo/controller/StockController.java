package com.lxa.cl.stocktwo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {
    @RequestMapping("/info")
    public String stockInfo(){
        return "=====stockInfo===two=";
    }
}
