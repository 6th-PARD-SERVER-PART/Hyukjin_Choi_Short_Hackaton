package com.pard.server.domdry.repository;

import com.pard.server.domdry.domain.Member;
import com.pard.server.domdry.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByMemberId(Long memberId);

    boolean existsByMemberIdAndPickupSchedulePlaceId(Long memberId, Long pickupSchedulePlaceId);
}
