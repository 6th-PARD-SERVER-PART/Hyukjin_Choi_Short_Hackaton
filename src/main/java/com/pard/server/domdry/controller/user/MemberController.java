package com.pard.server.domdry.controller.user;

import com.pard.server.domdry.dto.MemberCreateRequest;
import com.pard.server.domdry.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public ResponseEntity<Void> create(@RequestBody MemberCreateRequest request) {
        memberService.create(request);
        return ResponseEntity.ok().build();
    }


}
