package com.pard.server.domdry.dto;

import com.pard.server.domdry.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderResponse {
    Long orderId;
    OrderStatus stats;
}
