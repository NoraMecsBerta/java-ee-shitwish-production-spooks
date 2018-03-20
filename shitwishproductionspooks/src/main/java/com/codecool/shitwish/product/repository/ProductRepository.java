package com.codecool.shitwish.product.repository;

import com.codecool.shitwish.product.model.Product;
import com.codecool.shitwish.product.model.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByProductStatus(ProductStatus productStatus);
    Product findById(int id);
    List<Product> findByUserId(int userId);


    }
