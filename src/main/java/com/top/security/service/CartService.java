package com.top.security.service;

import com.top.security.domain.Cart;

import java.util.List;

public interface CartService {
    Cart getCart(Long itemId, Long memberId);

    List<Cart> getCartsByMember(Long memberId);

    void setCart(Long itemId, Long memberId);

    void deleteCart(Cart cart);

    void deleteCartByMemberId(Long memberId);
}
