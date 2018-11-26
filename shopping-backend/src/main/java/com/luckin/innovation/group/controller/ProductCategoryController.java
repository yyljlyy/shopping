package com.luckin.innovation.group.controller;

import com.luckin.innovation.group.entity.ProductCategory;
import com.luckin.innovation.group.entity.ResultMsg;
import com.luckin.innovation.group.service.ProductCategoryService;
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
@RequestMapping("/productcategory")
public class ProductCategoryController {
    @Resource
    private ProductCategoryService productcategoryService;

     /**
      * 进入列表页面
      *
      * @return
      */
     @RequestMapping(value = "index", method = {RequestMethod.GET})
        public String index() {
            return "productcategory/index";
     }

     /**
      * 进入编辑页面
      *
      * @return
      */
     @RequiresPermissions("auth:user:edit")
     @RequestMapping(value = "edit", method = {RequestMethod.GET})
     public String edit() {
            return "productcategory/edit";
     }

     /**
      * 进入新增页面
      *
      * @return
      */
     @RequestMapping(value = "add", method = {RequestMethod.GET})
        public String add() {
            return "productcategory/add";
     }

    @RequestMapping("productcategory/page")
    @ResponseBody
    public Page<ProductCategory> getList(String order, Integer offset, Integer limit){
        PageRequest pageRequest = PageRequest.of(offset, limit, new Sort(order.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, "Id"));
        return productcategoryService.getProductCategoryList(pageRequest);
    }

    @RequestMapping("productcategory/add")
    @ResponseBody
    public ResultMsg addProductCategory(ProductCategory data){
        Integer code = productcategoryService.saveProductCategory(data);
        return ResultMsg.ok();
    }

    @RequestMapping("productcategory/del")
    @ResponseBody
    public ResultMsg delProductCategory(Long id){
        Integer code = productcategoryService.deleteProductCategory(id);
        return ResultMsg.ok();
    }
}
