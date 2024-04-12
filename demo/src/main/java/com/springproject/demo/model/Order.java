package com.springproject.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    
    private UserModel user;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "product_id")
    // @JsonBackReference
    // private ProductBook product;

    // Constructors
    public Order() {
    }

    public Order(UserModel user) {
        this.user = user;
        // this.product = product;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    // public ProductBook getProduct() {
    //     return product;
    // }

    // public void setProduct(ProductBook product) {
    //     this.product = product;
    // }
}
