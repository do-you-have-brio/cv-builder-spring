package dyhb.api.controller;

import dyhb.api.database.repository.ResumeRepository;
import dyhb.api.dto.CreateResumeDTO;
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

  @GetMapping("/{userId}")
  public ResponseEntity<List<ResumeModel>> findByUserId(@PathVariable UUID userId) {
    List<ResumeModel> resumes = repository.findByUserId(userId);
    return !resumes.isEmpty()
        ? ResponseEntity.ok(resumes)
        : ResponseEntity.noContent().build();
  }

  @PostMapping("/{userId}")
  public ResponseEntity<ResumeModel> save(
      @RequestBody CreateResumeDTO dto, @PathVariable UUID userId) {
    var model = mapper.fromCreateDTOtoModel(dto, userId);

    var result = repository.save(model);
    return result != null
        ? ResponseEntity.status(201).body(result)
        : ResponseEntity.badRequest().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    boolean deleted = repository.delete(id);
    return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}
