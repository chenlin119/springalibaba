package com.lxa.cl.orderfeign.openfegin;

import org.springframework.stereotype.Component;

@Component
public class StockFeginImpl implements StockFeginService{


    @Override
    public String stockInfo() {
        return "降级了";
    }
}
