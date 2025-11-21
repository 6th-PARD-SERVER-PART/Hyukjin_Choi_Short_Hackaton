package com.pard.server.domdry.dto;

import com.pard.server.domdry.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminOrderResponse {
    private Long orderId;
    private String memberName;
    private String phoneNumber;
    private String placeName;
    private LocalDateTime pickupDate;
    private OrderStatus status;
}

