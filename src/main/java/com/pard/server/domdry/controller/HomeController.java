package com.pard.server.domdry.controller;

import com.pard.server.domdry.dto.HomeResponse;
import com.pard.server.domdry.service.HomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/home")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/{id}")
    public ResponseEntity<HomeResponse> getHome(@PathVariable Long id) {
        return ResponseEntity.ok(homeService.getOrdersForHome(id));
    }
}
