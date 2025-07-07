package dyhb.ai.controller;


import dyhb.ai.service.GeminiAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.*;

@RestController
@RequestMapping("/ai")
@AllArgsConstructor
public class AIController {

    @Autowired
    GeminiAIService geminiAIService;

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {

        return ResponseEntity.ok("pong!");
    }

}
