package com.top.security.repository;

import com.top.security.domain.Cart;
import com.top.security.domain.Item;
import com.top.security.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByMember(Member member);

    Cart findByMemberAndItem(Member member, Item item);

    void deleteCartByMemberId(Long memberId);
}
