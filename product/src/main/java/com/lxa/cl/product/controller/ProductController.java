package com.lxa.cl.product.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @RequestMapping("/{id}")
    public String productInfo(@PathVariable("id") String id){

        return "=====product===="+id;
    }
}
