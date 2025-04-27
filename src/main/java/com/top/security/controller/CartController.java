package com.top.security.controller;

import com.top.security.domain.Cart;
import com.top.security.domain.Item;
import com.top.security.service.CartService;
import com.top.security.service.JwtService;
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
public class CartController {

    private final CartService cartService;
    private final JwtService jwtService;

    @GetMapping("/cart/items")
    public ResponseEntity<?> getCartItems(
            @CookieValue(value = "token", required = false) String token){
        if(!jwtService.isValid(token))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        Long memberId = jwtService.getId(token);

        List<Cart> carts = cartService.getCartsByMember(memberId);
        List<Item> items = carts.stream().map(Cart::getItem).toList();

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping("/cart/items/{itemId}")
    public ResponseEntity<?> pushCartItem(
            @PathVariable("itemId") Long itemId,
            @CookieValue(value = "token", required = false) String token){
        if(!jwtService.isValid(token))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        Long memberId = jwtService.getId(token);
        Cart cart = cartService.getCart(itemId, memberId);
        if(cart == null)
            cartService.setCart(itemId, memberId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/cart/items/{itemId}")
    public ResponseEntity<?> removeCartItem(
            @PathVariable("itemId") Long itemId,
            @CookieValue(value = "token", required = false) String token) {
        if(!jwtService.isValid(token))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        Long memberId = jwtService.getId(token);

        Cart cart = cartService.getCart(itemId, memberId);

        cartService.deleteCart(cart);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
