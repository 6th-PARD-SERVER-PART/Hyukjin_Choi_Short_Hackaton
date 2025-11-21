package com.pard.server.domdry.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PickUpSchedulePlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long laundryId;

    @Column
    private Long pickUpScheduleId;

    @Column
    private Long pickUpPlaceId;

    @Column
    private Integer maxOrders;

    @Column
    private Integer currentOrders;
}
