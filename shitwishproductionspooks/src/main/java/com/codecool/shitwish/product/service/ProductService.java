package com.codecool.shitwish.product.service;


import com.codecool.shitwish.product.model.Product;
import com.codecool.shitwish.product.model.ProductStatus;
import com.codecool.shitwish.product.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private Product product;

    @Autowired
    private ProductRepository productRepository;

    /*public List<String> findAllActive() {
        return convertProductListToJsonStringList(productRepository.findByProductStatus(ProductStatus.ACTIVE));

    }*/

    public JSONObject findProductsByStatus(ProductStatus productStatus) throws JsonProcessingException {
        return convertProductListToJsonStringList(productRepository.findByProductStatus(productStatus));
    }


    public void addProduct(String name, ProductStatus productStatus, String image, String description, long price, Date date, int userId) {
        Product product = new Product(name, productStatus, image, description, price, date, userId);
        productRepository.save(product);
    }


    public JSONObject convertProductListToJsonStringList(List<Product> productList) throws JsonProcessingException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("products", productList);
        return jsonObject;
    }

    public JSONObject sampleProductFields() {
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("image", "valami.jpg");
        jsonObject.put("name", "something");
        jsonObject.put("description", "little desc");
        jsonObject.put("price", 120);
        jsonObject.put("productStatus", ProductStatus.IN_CART);
        jsonObject.put("date", new Date());
        jsonObject.put("userId", 3);
        return jsonObject;
    }
}
