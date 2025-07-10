package dyhb.api.controller;

import dyhb.api.database.repository.JobRepository;
import dyhb.api.dto.JobUpsertDto;
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

  @GetMapping("/{id}")
  public ResponseEntity<JobModel> findById(@PathVariable UUID id) {
    return repository
            .findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/{userId}")
  public ResponseEntity<List<JobModel>> findByUserId(@PathVariable UUID userId) {
    List<JobModel> jobs = repository.findByUserId(userId);
    return !jobs.isEmpty() ? ResponseEntity.ok(jobs) : ResponseEntity.noContent().build();
  }

  @PostMapping("/{userId}")
  public ResponseEntity<JobModel> save(@RequestBody JobUpsertDto dto, @PathVariable UUID userId) {
    var model = mapper.fromCreateDtoToModel(dto, userId);

    var result = repository.save(model);
    return result != null
        ? ResponseEntity.status(201).body(result)
        : ResponseEntity.badRequest().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<JobModel> update(@PathVariable UUID id, @RequestBody JobUpsertDto dto) {
    return repository
        .findById(id)
        .map(
            existingModel -> {
              mapper.updateModelFromDto(dto, existingModel);
              return ResponseEntity.ok(repository.save(existingModel));
            })
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    boolean deleted = repository.delete(id);
    return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}
