package com.codecool.shitwish.product.service;

import com.codecool.shitwish.product.model.ProductStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

//@Component
public class InitializerBean {

    public InitializerBean(ProductService productService){
        productService.addProduct("Alban bakery", ProductStatus.ACTIVE, ".jpg", "my uncle gave me as heritage this bakery but i dont like it", 120000, new Date(), 1);
        productService.addProduct("nose", ProductStatus.ACTIVE, ".jpg", "Michael's old nose", 1000000, new Date(), 2);
    }



}
