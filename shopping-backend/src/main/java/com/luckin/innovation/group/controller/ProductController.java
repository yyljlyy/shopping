package com.luckin.innovation.group.controller;

import com.luckin.innovation.group.entity.Product;
import com.luckin.innovation.group.entity.ResultMsg;
import com.luckin.innovation.group.service.ProductService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

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

    @RequestMapping("product/page")
    @ResponseBody
    public Page<Product> getList(String order, Integer offset, Integer limit){
        PageRequest pageRequest = new PageRequest(offset, limit, new Sort(order.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, "Id"));
        return productService.getProductList(pageRequest);
    }

    @RequestMapping("product/add")
    @ResponseBody
    public ResultMsg addProduct(Product product){
        Integer code = productService.saveProduct(product);
        return ResultMsg.ok();
    }

    @RequestMapping("product/del")
    @ResponseBody
    public ResultMsg delProduct(Long id){
        Integer code = productService.deleteProduct(id);
        return ResultMsg.ok();
    }
}
