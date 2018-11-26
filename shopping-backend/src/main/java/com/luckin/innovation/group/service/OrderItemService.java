package com.luckin.innovation.group.service;

import com.luckin.innovation.group.entity.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface OrderItemService {
    Page<OrderItem> getOrderItemList(PageRequest pageRequest);

    Integer saveOrderItem(OrderItem orderitem);

    Integer deleteOrderItem(Long id);

    OrderItem updateOrderItem(OrderItem orderitem);

    OrderItem findById(Long id);

    List<OrderItem> findAll();
}
