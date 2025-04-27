package com.top.security.service;

import com.top.security.domain.Order;
import com.top.security.dto.OrderDto;

import java.util.List;

public interface OrderService {
    void setOrder(OrderDto orderDto, Long memberId);

    List<Order> getAll(Long memberId);
}
