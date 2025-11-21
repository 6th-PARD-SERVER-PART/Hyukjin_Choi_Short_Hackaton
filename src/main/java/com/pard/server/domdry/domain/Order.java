package com.pard.server.domdry.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long memberId;

    @Column
    private Long laundryId;

    @Column
    private Long pickupSchedulePlaceId;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public static Order of(Long memberId, Long laundryId, Long pickupSchedulePlaceId){
        return Order.builder()
                .memberId(memberId)
                .laundryId(laundryId)
                .pickupSchedulePlaceId(pickupSchedulePlaceId)
                .status(OrderStatus.WAITING_PAYMENT)
                .build();
    }

    public void markPaid(){
        this.status = OrderStatus.PAID;
    }
}
