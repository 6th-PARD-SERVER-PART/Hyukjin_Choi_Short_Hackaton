package com.pard.server.domdry.service;

import com.pard.server.domdry.domain.*;
import com.pard.server.domdry.dto.AdminOrderResponse;
import com.pard.server.domdry.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminOrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final PickUpScheduleRepository scheduleRepository;
    private final PickUpSchedulePlaceRepository pspRepository;
    private final PickUpPlaceRepository placeRepository;

    // 주문 조회
    public List<AdminOrderResponse> getOrdersByStatus() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(this::convertToResponse).toList();
    }

    // 입금 처리 확인
    @Transactional
    public void confirmOrder(Long orderId) {

        // 1) 주문 가져오기
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        // 2) 상태 검증
        if (order.getStatus() != OrderStatus.WAITING_PAYMENT) {
            throw new IllegalStateException("Order cannot be confirmed");
        }

        // 3) 상태 변경
        order.markPaid();

        // 4) PSP(currentOrders++)
        PickUpSchedulePlace psp = pspRepository.findById(order.getPickupSchedulePlaceId())
                .orElseThrow(() -> new IllegalArgumentException("PSP not found"));

        psp.increaseCurrentOrders();
    }

    // DTO 변환 전담 메서드
    private AdminOrderResponse convertToResponse(Order order) {

        Member member = memberRepository.findById(order.getMemberId())
                .orElseThrow();

        PickUpSchedulePlace psp = pspRepository.findById(order.getPickupSchedulePlaceId())
                .orElseThrow();

        PickUpPlace place = placeRepository.findById(psp.getPickUpPlaceId())
                .orElseThrow();

        PickUpSchedule schedule = scheduleRepository.findById(psp.getPickUpScheduleId())
                .orElseThrow();

        return AdminOrderResponse.builder()
                .orderId(order.getId())
                .memberName(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .placeName(place.getName())
                .pickupDate(schedule.getPickUpTime())
                .status(order.getStatus())
                .build();
    }
}
