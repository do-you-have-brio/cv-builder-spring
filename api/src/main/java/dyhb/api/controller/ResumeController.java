package dyhb.api.controller;

import dyhb.api.database.repository.ResumeRepository;
import dyhb.api.dto.ResumeUpsertDto;
import dyhb.api.database.models.ResumeModel;
import dyhb.api.mappers.ResumeMapper;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/resume")
@AllArgsConstructor
public class ResumeController {

  @Autowired private final ResumeRepository repository;

  private final ResumeMapper mapper = Mappers.getMapper(ResumeMapper.class);

  @GetMapping("/{id}")
  public ResponseEntity<ResumeModel> findById(@PathVariable UUID id) {
    return repository
        .findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<ResumeModel>> findByUserId(@PathVariable UUID userId) {
    List<ResumeModel> resumes = repository.findByUserId(userId);
    return !resumes.isEmpty() ? ResponseEntity.ok(resumes) : ResponseEntity.noContent().build();
  }

  @PostMapping("/user/{userId}")
  public ResponseEntity<List<ResumeModel>> saveAll(
      @RequestBody List<ResumeUpsertDto> dtos, @PathVariable UUID userId) {
    var models = mapper.fromCreateDtosToModels(dtos, userId);
    var result = repository.saveAll(models);
    return result != null
        ? ResponseEntity.status(201).body(result)
        : ResponseEntity.badRequest().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResumeModel> update(
      @PathVariable UUID id, @RequestBody ResumeUpsertDto dto) {
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
