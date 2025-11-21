package com.pard.server.domdry.dto;

import com.pard.server.domdry.domain.PickUpPlace;
import com.pard.server.domdry.domain.PickUpSchedulePlace;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceScheduleResponse {
    Long schedulePlaceId;
    String placeName;
    Integer currentOrders;
    Integer maxOrders;

    public static PlaceScheduleResponse from(PickUpSchedulePlace sp, PickUpPlace place) {
        return PlaceScheduleResponse.builder()
                .schedulePlaceId(sp.getId())
                .placeName(place.getName())
                .currentOrders(sp.getCurrentOrders())
                .maxOrders(sp.getMaxOrders())
                .build();
    }
}
