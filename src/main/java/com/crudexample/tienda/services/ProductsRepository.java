package com.crudexample.tienda.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crudexample.tienda.models.Product;

public interface ProductsRepository extends JpaRepository<Product, Integer> {

}
