package com.luckin.innovation.group.service;

import com.luckin.innovation.group.entity.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProductCategoryService {
    Page<ProductCategory> getProductCategoryList(PageRequest pageRequest);

    Integer saveProductCategory(ProductCategory data);

    Integer deleteProductCategory(Long id);

    ProductCategory updateProductCategory(ProductCategory permission);

    ProductCategory findById(Long id);

    List<ProductCategory> findAll();
}
