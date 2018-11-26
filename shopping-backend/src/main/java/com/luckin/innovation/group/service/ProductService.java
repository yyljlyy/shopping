package com.luckin.innovation.group.service;

import com.luckin.innovation.group.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProductService {
    Page<Product> getProductList(PageRequest pageRequest);

    Integer saveProduct(Product product);

    Integer deleteProduct(Long id);

    Product updateProduct(Product product);

    Product findById(Long id);

    List<Product> findAll();
}
