package com.top.security.service.serviceImpl;

import com.top.security.domain.Cart;
import com.top.security.domain.Item;
import com.top.security.domain.Member;
import com.top.security.domain.Order;
import com.top.security.dto.OrderDto;
import com.top.security.repository.CartRepository;
import com.top.security.repository.ItemRepository;
import com.top.security.repository.MemberRepository;
import com.top.security.repository.OrderRepository;
import com.top.security.service.CartService;
import com.top.security.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public void setOrder(OrderDto orderDto, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다."));

        Order newOrder = Order.builder().
                member(member).
                name(orderDto.getName()).
                address(orderDto.getAddress()).
                payment(orderDto.getPayment()).
                cardNumber(orderDto.getCardNumber()).
                items(orderDto.getItems()).
                build();

        orderRepository.save(newOrder);
    }

    @Override
    public List<Order> getAll(Long memberId) {

        return orderRepository.findByMemberIdOrderByIdDesc(memberId);
    }
}
