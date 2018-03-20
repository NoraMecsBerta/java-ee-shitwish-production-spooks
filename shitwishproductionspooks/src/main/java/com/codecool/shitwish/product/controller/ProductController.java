package com.codecool.shitwish.product.controller;

import com.codecool.shitwish.product.model.Product;
import com.codecool.shitwish.product.model.ProductStatus;
import com.codecool.shitwish.product.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.org.apache.regexp.internal.RE;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    /*@GetMapping(value = "/active-products")
    public String getActiveProducts() throws JsonProcessingException {
        System.out.println(productService.findAllActive());
        return productService.findAllActive();
    }*/


    @GetMapping(value = "/active-products")
    public ResponseEntity getActiveProducts() throws JsonProcessingException {
        System.out.println(productService.findProductsByStatus(ProductStatus.ACTIVE));
        return new ResponseEntity<>(productService.findProductsByStatus(ProductStatus.ACTIVE).toString(), HttpStatus.OK);
    }


    @GetMapping(value = "/ordered-products")
    public ResponseEntity getOrderedProducts() throws JsonProcessingException {
        System.out.println(productService.findProductsByStatus(ProductStatus.IN_CART));
        return new ResponseEntity<>(productService.findProductsByStatus(ProductStatus.IN_CART).toString(), HttpStatus.OK);
    }

    @GetMapping(value = "/sold-products")
    public ResponseEntity getSoldProducts() throws JsonProcessingException {
        System.out.println(productService.findProductsByStatus(ProductStatus.SOLD));
        return new ResponseEntity<>(productService.findProductsByStatus(ProductStatus.SOLD).toString(), HttpStatus.OK);
    }

    /*@GetMapping(value = "/sampleproduct")
    public HttpStatus getOneProduct() {
        JSONObject jsonObject = productService.sampleProductFields();
        String name = (String)jsonObject.get("name");
        ProductStatus productStatus = (ProductStatus) jsonObject.get("productStatus");
        String description = (String) jsonObject.get("description");
        String image = (String) jsonObject.get("image");
        int userId = (Integer) jsonObject.get("userId");
        Date date = (Date) jsonObject.get("date");
        int price =(Integer) jsonObject.get("price");
        productService.addProduct(name, productStatus, image, description, price, date, userId);
        return HttpStatus.OK;
    }*/

    @GetMapping(value = "/add-product")
    public ResponseEntity addProduct() {
        JSONObject jsonObject = productService.sampleProductFields();
        String name = (String)jsonObject.get("name");
        ProductStatus productStatus = (ProductStatus) jsonObject.get("productStatus");
        String description = (String) jsonObject.get("description");
        String image = (String) jsonObject.get("image");
        int userId = (Integer) jsonObject.get("userId");
        Date date = (Date) jsonObject.get("date");
        int price =(Integer) jsonObject.get("price");
        productService.addProduct(name, productStatus, image, description, price, date, userId);
        return new ResponseEntity(HttpStatus.OK);

    }
}
