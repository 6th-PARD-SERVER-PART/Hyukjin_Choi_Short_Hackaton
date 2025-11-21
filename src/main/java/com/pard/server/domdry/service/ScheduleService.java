package com.pard.server.domdry.service;

import com.pard.server.domdry.domain.PickUpPlace;
import com.pard.server.domdry.domain.PickUpSchedulePlace;
import com.pard.server.domdry.dto.PlaceScheduleResponse;
import com.pard.server.domdry.dto.ScheduleResponse;
import com.pard.server.domdry.repository.PickUpPlaceRepository;
import com.pard.server.domdry.repository.PickUpSchedulePlaceRepository;
import com.pard.server.domdry.repository.PickUpScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final PickUpScheduleRepository pickUpScheduleRepository;
    private final PickUpSchedulePlaceRepository pickUpSchedulePlaceRepository;
    private final PickUpPlaceRepository pickUpPlaceRepository;

    public List<ScheduleResponse> getAllSchedules() {
        return pickUpScheduleRepository.findAll().stream()
                .map(s -> new ScheduleResponse(
                        s.getId(),
                        s.getPickUpTime()
                )).toList();
    }

    public List<PlaceScheduleResponse> getPlacesBySchedule(Long scheduleId) {

        List<PickUpSchedulePlace> schedulePlaces = pickUpSchedulePlaceRepository.findByPickUpScheduleId(scheduleId);

        return schedulePlaces.stream()
                .map(sp -> {
                    PickUpPlace place = pickUpPlaceRepository.findById(sp.getPickUpPlaceId())
                            .orElseThrow(() -> new IllegalArgumentException("Invalid place id"));

                    return PlaceScheduleResponse.from(sp, place);
                })
                .toList();
    }
}
