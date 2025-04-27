package com.top.security.controller;

import com.top.security.domain.Order;
import com.top.security.dto.OrderDto;
import com.top.security.service.CartService;
import com.top.security.service.JwtService;
import com.top.security.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Log4j2
public class OrderController {

    private final OrderService orderService;
    private final JwtService jwtService;
    private final CartService cartService;

    @PostMapping("/orders")
    public ResponseEntity<?> pushOrder(
            @RequestBody OrderDto orderDto,
            @CookieValue(value = "token", required = false) String token){
        if(!jwtService.isValid(token))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        Long memberId = jwtService.getId(token);

        orderService.setOrder(orderDto, memberId);
        cartService.deleteCartByMemberId(memberId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getOrder(
            @CookieValue(value = "token", required = false) String token){
        if(!jwtService.isValid(token))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        Long memberId = jwtService.getId(token);

        List<Order> orderList = orderService.getAll(memberId);

        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }
}
