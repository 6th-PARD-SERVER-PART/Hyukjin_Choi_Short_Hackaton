package com.pard.server.domdry.controller.admin;

import com.pard.server.domdry.dto.AdminOrderResponse;
import com.pard.server.domdry.service.AdminOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/admin/orders")
@RequiredArgsConstructor
public class AdminController {

    private final AdminOrderService adminOrderService;

    @GetMapping
    public ResponseEntity<List<AdminOrderResponse>> getOrders() {
        return ResponseEntity.ok(adminOrderService.getOrdersByStatus());
    }

    // 주문 확인
    @PatchMapping("/{orderId}/confirm")
    public ResponseEntity<Void> confirm(@PathVariable Long orderId) {
        adminOrderService.confirmOrder(orderId);
        return ResponseEntity.ok().build();
    }
}
