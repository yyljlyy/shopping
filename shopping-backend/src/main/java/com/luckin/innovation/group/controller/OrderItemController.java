package com.luckin.innovation.group.controller;

import com.luckin.innovation.group.entity.OrderItem;
import com.luckin.innovation.group.entity.ResultMsg;
import com.luckin.innovation.group.service.OrderItemService;
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
@RequestMapping("/orderitem")
public class OrderItemController {
    @Resource
    private OrderItemService orderitemService;

     /**
      * 进入列表页面
      *
      * @return
      */
     @RequestMapping(value = "index", method = {RequestMethod.GET})
        public String index() {
            return "orderitem/index";
     }

     /**
      * 进入编辑页面
      *
      * @return
      */
     @RequiresPermissions("auth:user:edit")
        @RequestMapping(value = "edit", method = {RequestMethod.GET})
        public String edit() {
            return "orderitem/edit";
     }

     /**
      * 进入新增页面
      *
      * @return
      */
     @RequestMapping(value = "add", method = {RequestMethod.GET})
        public String add() {
            return "orderitem/add";
     }

    @RequestMapping("orderitem/page")
    @ResponseBody
    public Page<OrderItem> getList(String order, Integer offset, Integer limit){
        PageRequest pageRequest = new PageRequest(offset, limit, new Sort(order.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, "Id"));
        return orderitemService.getOrderItemList(pageRequest);
    }

    @RequestMapping("orderitem/add")
    @ResponseBody
    public ResultMsg addOrderItem(OrderItem orderitem){
        Integer code = orderitemService.saveOrderItem(orderitem);
        return ResultMsg.ok();
    }

    @RequestMapping("orderitem/del")
    @ResponseBody
    public ResultMsg delOrderItem(Long id){
        Integer code = orderitemService.deleteOrderItem(id);
        return ResultMsg.ok();
    }
}
