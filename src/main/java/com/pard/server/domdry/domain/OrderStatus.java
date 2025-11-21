package com.pard.server.domdry.domain;

public enum OrderStatus {
    WAITING_PAYMENT,  // 결제 전
    PAID,             // 결제 완료
//    CANCELLED,        // 결제 실패 or 사용자 취소
    PICKED_UP,        // 수거 완료
//    WASHING,          // 세탁 중
//    DELIVERED,        // 기숙사로 배송 완료
    COMPLETED,        // 사용자 최종 완료
//    REFUNDED          // 환불됨
}
