package com.pard.server.domdry.controller.user;

import com.pard.server.domdry.dto.PlaceScheduleResponse;
import com.pard.server.domdry.dto.ScheduleResponse;
import com.pard.server.domdry.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.getAllSchedules());
    }

    @GetMapping("/{scheduleId}/places")
    public ResponseEntity<List<PlaceScheduleResponse>> getPlaceBySchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(scheduleService.getPlacesBySchedule(scheduleId));
    }
}
