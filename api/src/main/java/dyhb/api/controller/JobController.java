package dyhb.api.controller;

import dyhb.api.database.repository.JobRepository;
import dyhb.api.dto.CreateJobDTO;
import dyhb.api.database.models.JobModel;
import dyhb.api.mappers.JobMapper;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/job")
@AllArgsConstructor
public class JobController {

  @Autowired private final JobRepository repository;

  private final JobMapper mapper = Mappers.getMapper(JobMapper.class);

  @GetMapping("/{userId}")
  public ResponseEntity<List<JobModel>> findByUserId(@PathVariable UUID userId) {
    List<JobModel> jobs = repository.findByUserId(userId);
    if (jobs.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(jobs);
  }

  @PostMapping("/{userId}")
  public ResponseEntity<JobModel> save(@RequestBody CreateJobDTO dto, @PathVariable UUID userId) {
    var model = mapper.fromCreateDTOtoModel(dto, userId);
    var result = repository.save(model);

    if (result == null) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.status(201).body(result);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    boolean deleted = repository.delete(id);
    return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}
