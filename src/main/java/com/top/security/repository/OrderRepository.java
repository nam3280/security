package com.top.security.repository;

import com.top.security.domain.Member;
import com.top.security.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByMemberIdOrderByIdDesc(Long memberId);
}
