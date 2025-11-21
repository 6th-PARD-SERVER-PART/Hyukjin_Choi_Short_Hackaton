package com.pard.server.domdry.dto;

import com.pard.server.domdry.domain.PickUpSchedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleResponse {
    Long scheduleId;
    String date;

    public static ScheduleResponse from(PickUpSchedule entity) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일");
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEEE", Locale.KOREAN);

        LocalDate date = entity.getPickUpTime().toLocalDate();

        String formattedDate = date.format(dateFormatter);
        String dayOfWeek = date.format(dayFormatter); // 월요일, 화요일 …

        return ScheduleResponse.builder()
                .scheduleId(entity.getId())
                .date(formattedDate + " (" + dayOfWeek.charAt(0) + ")")
                // → "2026년 11월 26일 (목)"
                .build();
    }

}
