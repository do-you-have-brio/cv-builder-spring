package dyhb.api.controller;

import dyhb.api.dto.CreateResumeDTO;
import dyhb.api.entities.Resume;
import dyhb.api.service.ResumeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/resume")
@AllArgsConstructor
public class ResumeController {

    @Autowired
    private final ResumeService resumeService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Resume>> getResume(@PathVariable UUID userId) {
        List<Resume> resumes = resumeService.getResumeByUserId(userId);
        if (resumes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resumes);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Resume> addResume(@RequestBody CreateResumeDTO createResumeDTO, @PathVariable UUID userId) {
        Resume resume = resumeService.addResume(createResumeDTO, userId);
        if (resume == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(201).body(resume);
    }
}
