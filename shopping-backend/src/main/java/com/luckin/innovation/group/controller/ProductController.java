package com.luckin.innovation.group.controller;

import com.luckin.innovation.group.entity.Product;
import com.luckin.innovation.group.entity.ResultMsg;
import com.luckin.innovation.group.service.ProductService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;

     /**
      * 进入列表页面
      *
      * @return
      */
     @RequestMapping(value = "index", method = {RequestMethod.GET})
        public String index() {
            return "product/index";
     }

     /**
      * 进入编辑页面
      *
      * @return
      */
     @RequiresPermissions("auth:user:edit")
        @RequestMapping(value = "edit", method = {RequestMethod.GET})
        public String edit() {
            return "product/edit";
     }

     /**
      * 进入新增页面
      *
      * @return
      */
     @RequestMapping(value = "add", method = {RequestMethod.GET})
        public String add() {
            return "product/add";
     }

    @RequestMapping("page")
    @ResponseBody
    public Page<Product> getList(Integer pageNum, Integer pageSize){
        PageRequest pageRequest = PageRequest.of(pageNum -1 , pageSize);
        return productService.getProductList(pageRequest);
    }

    @RequestMapping("add")
    @ResponseBody
    public ResultMsg addProduct(Product product){
        Integer code = productService.saveProduct(product);
        return ResultMsg.ok();
    }

    @RequestMapping("del")
    @ResponseBody
    public ResultMsg delProduct(Long id){
        Integer code = productService.deleteProduct(id);
        return ResultMsg.ok();
    }
}
