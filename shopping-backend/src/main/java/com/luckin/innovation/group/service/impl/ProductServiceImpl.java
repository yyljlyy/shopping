package com.luckin.innovation.group.service.impl;

import com.luckin.innovation.group.dao.ProductRepository;
import com.luckin.innovation.group.entity.Product;
import com.luckin.innovation.group.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductRepository productRepository;

    @Override
    public Page<Product> getProductList(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest);
    }

    @Override
    public Integer saveProduct(Product product) {
        try {
            Product save = productRepository.save(product);
        }catch (Exception e){
            System.out.println(e);
            return 1;
        }
        return 0;
    }

    @Override
    public Integer deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
        }catch (Exception e){
            System.out.println(e);
            return 1;
        }
        return 0;
    }

    @Override
    public Product updateProduct(Product product){
         //todo
         return productRepository.save(product);
    }

    @Override
    public Product findById(Long id){
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> findAll(){
        return productRepository.findAll();
    }
}
