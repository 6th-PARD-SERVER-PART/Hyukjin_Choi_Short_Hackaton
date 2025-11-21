package com.pard.server.domdry.service;

import com.pard.server.domdry.domain.Order;
import com.pard.server.domdry.domain.PickUpSchedulePlace;
import com.pard.server.domdry.dto.CreateOrderRequest;
import com.pard.server.domdry.dto.CreateOrderResponse;
import com.pard.server.domdry.repository.OrderRepository;
import com.pard.server.domdry.repository.PickUpSchedulePlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final PickUpSchedulePlaceRepository pickUpSchedulePlaceRepository;

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        Long memberId = request.getMemberId();
        Long pickupSchedulePlaceId = request.getPickupSchedulePlaceId();

        // 1) 조합 엔티티 조회
        PickUpSchedulePlace schedulePlace = pickUpSchedulePlaceRepository.findById(pickupSchedulePlaceId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 픽업 스케줄/기숙사 정보입니다."));

        // 2) 이미 해당 슬롯에 주문한 적 있는지 체크 (중복 방지)
        boolean exists = orderRepository.existsByMemberIdAndPickupSchedulePlaceId(memberId, pickupSchedulePlaceId);
        if (exists) {
            throw new IllegalStateException("이미 해당 픽업에 주문이 존재합니다.");
        }

        // 3) 정원 초과 체크
        if (schedulePlace.getCurrentOrders() >= schedulePlace.getMaxOrders()) {
            throw new IllegalStateException("해당 기숙사의 픽업 가능 인원이 마감되었습니다.");
        }

        // 4) Order 생성 (laundryId는 schedulePlace에 있는 값 사용)
        Order order = Order.of(memberId, schedulePlace.getLaundryId(), pickupSchedulePlaceId);
        Order saved = orderRepository.save(order);

        // 5) currentOrders 증가
        // JPA dirty checking으로 자동 update 됨
        schedulePlace.increaseCurrentOrders();

        // 6) 응답 생성
        return new CreateOrderResponse(saved.getId(), saved.getStatus());
    }
}
