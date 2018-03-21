package com.codecool.shitwish.product.service;


import com.codecool.shitwish.product.model.Product;
import com.codecool.shitwish.product.model.ProductStatus;
import com.codecool.shitwish.product.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private Product product;

    @Autowired
    private ProductRepository productRepository;


    public JSONObject findProductsByStatus(ProductStatus productStatus) throws JsonProcessingException, NullPointerException {
        return convertProductListToJsonStringList(productRepository.findByProductStatus(productStatus));
    }

    public JSONObject getProductByProductId(int id) {
        if (productRepository.findById(id) != null) {
            return new JSONObject(productRepository.findById(id));
        } else {
            throw new NullPointerException("id not found");
        }
    }

    public JSONObject getProductByUserId(int id) throws JsonProcessingException, NullPointerException {
        if (productRepository.findByUserId(id) != null) {
            return convertProductListToJsonStringList(productRepository.findByUserId(id));
        } else {
            throw new NullPointerException("user id not found");
        }
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

    public void editProductByProductId(int id, String status) {
        Product productToEdit = productRepository.findById(id);
        if (status.equals("c")) {
            productToEdit.setProductStatus(ProductStatus.IN_CART);
        } else if (status.equals("a")) {
            productToEdit.setProductStatus(ProductStatus.ACTIVE);
        } else if (status.equals("s")) {
            productToEdit.setProductStatus(ProductStatus.SOLD);
        }
        productRepository.save(productToEdit);
    }

    public void setProductStatusToSold(List<Integer> productList) {
        for (int id : productList) {
            Product productToEdit = productRepository.findById(id);
            productToEdit.setProductStatus(ProductStatus.SOLD);
            productRepository.save(productToEdit);
        }
    }

    //it has to be deleted
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
