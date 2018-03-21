package com.codecool.shitwish.product.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PRODUCT")
@Component
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    private String name;

    @Enumerated(value = EnumType.STRING)
    private ProductStatus productStatus;

    private String image;

    private String description;

    private long price;

    private Date date;

    private int userId;



    public Product() { }

    public Product(String name, ProductStatus productStatus, String image, String description, long price, Date date, int userId) {
        this.name = name;
        this.productStatus = productStatus;
        this.image = image;
        this.description = description;
        this.price = price;
        this.date = date;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
