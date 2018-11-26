package com.luckin.innovation.group.service.impl;

import com.luckin.innovation.group.dao.OrderItemRepository;
import com.luckin.innovation.group.entity.OrderItem;
import com.luckin.innovation.group.service.OrderItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Resource
    private OrderItemRepository orderitemRepository;

    @Override
    public Page<OrderItem> getOrderItemList(PageRequest pageRequest) {
        return orderitemRepository.findAll(pageRequest);
    }

    @Override
    public Integer saveOrderItem(OrderItem orderitem) {
        try {
            OrderItem save = orderitemRepository.save(orderitem);
        }catch (Exception e){
            System.out.println(e);
            return 1;
        }
        return 0;
    }

    @Override
    public Integer deleteOrderItem(Long id) {
        try {
            orderitemRepository.deleteById(id);
        }catch (Exception e){
            System.out.println(e);
            return 1;
        }
        return 0;
    }

    @Override
    public OrderItem updateOrderItem(OrderItem orderitem){
         //todo
         return orderitemRepository.save(orderitem);
    }

    @Override
    public OrderItem findById(Long id){
        return orderitemRepository.findById(id).get();
    }

    @Override
    public List<OrderItem> findAll(){
        return orderitemRepository.findAll();
    }
}
