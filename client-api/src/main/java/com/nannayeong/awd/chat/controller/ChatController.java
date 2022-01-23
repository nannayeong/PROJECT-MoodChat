package com.nannayeong.awd.chat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/chat")
public class ChatController {

    @GetMapping(value = "")
    public ResponseEntity saveChat(String content) {

        log.info("[chat - save] content: {}", content);

        return ResponseEntity.accepted().body("");
    }
}
