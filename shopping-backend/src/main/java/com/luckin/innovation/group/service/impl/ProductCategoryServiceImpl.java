package com.luckin.innovation.group.service.impl;

import com.luckin.innovation.group.dao.ProductCategoryRepository;
import com.luckin.innovation.group.entity.ProductCategory;
import com.luckin.innovation.group.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productcategoryRepository;

    @Override
    public Page<ProductCategory> getProductCategoryList(PageRequest pageRequest) {
        return productcategoryRepository.findAll(pageRequest);
    }

    @Override
    public Integer saveProductCategory(ProductCategory data) {
        try {
            ProductCategory save = productcategoryRepository.save(data);
        }catch (Exception e){
            System.out.println(e);
            return 1;
        }
        return 0;
    }

    @Override
    public Integer deleteProductCategory(Long id) {
        try {
            productcategoryRepository.deleteById(id);
        }catch (Exception e){
            System.out.println(e);
            return 1;
        }
        return 0;
    }

    @Override
    public ProductCategory updateProductCategory(ProductCategory productcategory){
         return productcategoryRepository.save(productcategory);
    }

    @Override
    public ProductCategory findById(Long id){
        return productcategoryRepository.findById(id).get();
    }

    @Override
    public List<ProductCategory> findAll(){
        return productcategoryRepository.findAll();
    }
}
