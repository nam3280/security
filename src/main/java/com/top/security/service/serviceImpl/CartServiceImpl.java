package com.top.security.service.serviceImpl;

import com.top.security.domain.Cart;
import com.top.security.domain.Item;
import com.top.security.domain.Member;
import com.top.security.repository.CartRepository;
import com.top.security.repository.ItemRepository;
import com.top.security.repository.MemberRepository;
import com.top.security.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Override
    public Cart getCart(Long itemId, Long memberId) {
        log.info("getCart");

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다."));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("해당 상품이 존재하지 않습니다."));

        return cartRepository.findByMemberAndItem(member, item);
    }

    @Override
    public List<Cart> getCartsByMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다."));

        return cartRepository.findByMember(member);
    }

    @Transactional
    @Override
    public void setCart(Long itemId, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다."));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("해당 상품이 존재하지 않습니다."));

        Cart cart = Cart.builder().member(member).item(item).build();

        cartRepository.save(cart);
    }

    @Transactional
    @Override
    public void deleteCart(Cart cart) {
        cartRepository.delete(cart);
    }

    @Transactional
    @Override
    public void deleteCartByMemberId(Long memberId) {
        cartRepository.deleteCartByMemberId(memberId);
    }
}
