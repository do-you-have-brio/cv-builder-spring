package dyhb.api.controller;

import dyhb.api.dto.CreateJobDTO;
import dyhb.api.database.models.Job;
import dyhb.api.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/job")
@AllArgsConstructor
public class JobController {

    @Autowired
    private final JobService jobService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Job>> getJobsByUserId(@PathVariable UUID userId) {
        List<Job> jobs = jobService.getJobsByUserId(userId);
        if (jobs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(jobs);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Job> addJob(@RequestBody CreateJobDTO jobDTO, @PathVariable UUID userId) {
        Job job = jobService.addJob(jobDTO, userId);
        if (job == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(201).body(job);

    }

}
