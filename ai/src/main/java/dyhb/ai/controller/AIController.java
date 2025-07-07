package dyhb.ai.controller;


import dyhb.ai.dto.CreateResumeDTO;
import dyhb.ai.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.*;

@RestController
@RequestMapping("/ai")
@AllArgsConstructor
public class AIController {

    @Autowired
    AIService AIService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateResume(@RequestBody CreateResumeDTO dto) {
        var resume = AIService.generateResumePdf(dto);
        return ResponseEntity.ok(resume);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong!");
    }
}
