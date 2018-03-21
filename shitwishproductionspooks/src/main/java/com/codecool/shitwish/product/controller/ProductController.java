package com.codecool.shitwish.product.controller;

import com.codecool.shitwish.product.model.Product;
import com.codecool.shitwish.product.model.ProductStatus;
import com.codecool.shitwish.product.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;


@Controller
public class ProductController {

    @Autowired
    ProductService productService;


    @GetMapping(value = "/product/active-products")
    public ResponseEntity getActiveProducts() throws JsonProcessingException {
        System.out.println(productService.findProductsByStatus(ProductStatus.ACTIVE));
        try {
            return new ResponseEntity<>(productService.findProductsByStatus(ProductStatus.ACTIVE).toString(), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/product/by-id/{id}")
    public ResponseEntity getProductById(@PathVariable int id) {
        try {
            System.out.println(productService.getProductByProductId(id));
            productService.getProductByProductId(id);
            return new ResponseEntity<>(productService.getProductByProductId(id).toString(), HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/product/by-user/{userId}")
    public ResponseEntity getProductsByUser(@PathVariable int userId) throws JsonProcessingException {
        try {
            productService.getProductByUserId(userId);
            return new ResponseEntity<>(productService.getProductByUserId(userId).toString(), HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    //exception handling is missing
    @PostMapping(value = "/product/add-product")
    public ResponseEntity sampleAddProduct(@RequestParam JSONObject jsonObject) {
        //todo
        //we have to read out the json object from a route in main and after
        //save the entity like above
        //this should be post mapping
        //jsonobject from url
        //JSONObject jsonObject = productService.sampleProductFields();
        String name = (String) jsonObject.get("name");
        ProductStatus productStatus = (ProductStatus) jsonObject.get("productStatus");
        String description = (String) jsonObject.get("description");
        String image = (String) jsonObject.get("image");
        int userId = (Integer) jsonObject.get("userId");
        Date date = (Date) jsonObject.get("date");
        int price = (Integer) jsonObject.get("price");
        productService.addProduct(name, productStatus, image, description, price, date, userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    //look up and todo exception handling
    @PostMapping(value = "/product/edit-product-status/{productId}")
    public ResponseEntity editProductStatusById(@PathVariable int productId, @RequestParam JSONObject jsonObject) {
        //the 2 lines are temporary they have to be deleted, is it necessary at all?
        //statuses can go on line by line
        //JSONObject jsonObject = new JSONObject();
        //jsonObject.put("productStatus", ProductStatus.IN_CART);
        String status = (String) jsonObject.get("status");
        productService.editProductByProductId(productId, status);
        return new ResponseEntity(HttpStatus.OK);
    }

    // todo exception handling
    @PostMapping(value = "/product/remove-products")
    public ResponseEntity productStatusSetToSold(@RequestParam JSONObject jsonObject) {
        List<Integer> productIdList = (List<Integer>) jsonObject.get("products");
        productService.setProductStatusToSold(productIdList);
        return new ResponseEntity(HttpStatus.OK);
    }


    //are they necessary???
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

}
