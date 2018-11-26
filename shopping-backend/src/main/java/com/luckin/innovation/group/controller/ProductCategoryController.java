package com.luckin.innovation.group.controller;

import com.luckin.innovation.group.entity.ProductCategory;
import com.luckin.innovation.group.entity.ResultMsg;
import com.luckin.innovation.group.service.ProductCategoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import java.util.List;

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

    @RequestMapping("page")
    @ResponseBody
    public ResultMsg getList(Integer pageNum, Integer pageSize){
        PageRequest pageRequest = PageRequest.of(pageNum -1 , pageSize);
        ResultMsg result=new ResultMsg();
        result.setData(productcategoryService.getProductCategoryList(pageRequest));
        return result;
    }

    @RequestMapping("add")
    @ResponseBody
    public ResultMsg addProductCategory(ProductCategory productcategory){
        Integer code = productcategoryService.saveProductCategory(productcategory);
        return ResultMsg.ok();
    }

    @RequestMapping("del")
    @ResponseBody
    public ResultMsg delProductCategory(Long id){
        Integer code = productcategoryService.deleteProductCategory(id);
        return ResultMsg.ok();
    }

    @RequestMapping("findById")
    @ResponseBody
    public ResultMsg findById(Long id){
        ProductCategory byId = productcategoryService.findById(id);
        ResultMsg msg = new ResultMsg();
        msg.setData(byId);
        return msg;
    }

    /**
     * 查询全部数据操作
     * @return ResultMsg
     */
    @RequestMapping(value="all",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg all(){
        List<ProductCategory> coreMenus = productcategoryService.findAll();
        ProductCategory coreMenu=new ProductCategory();
        coreMenu.setId(0L);
        coreMenu.setCategoryName("/根目录");
        //向菜单list第一个位置插入根目录
        coreMenus.add(0,coreMenu);
        ResultMsg result=new ResultMsg();
        result.setData(coreMenus);
        return  result;
    }
}
