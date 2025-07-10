package dyhb.api.controller;

import dyhb.api.service.ProjectService;
import dyhb.api.dto.ProjectUpsertDto;
import dyhb.api.database.models.ProjectModel;
import dyhb.api.mappers.ProjectMapper;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/project")
@AllArgsConstructor
public class ProjectsController {

  @Autowired private final ProjectService service;

  private final ProjectMapper mapper = Mappers.getMapper(ProjectMapper.class);

  @GetMapping("/{id}")
  public ResponseEntity<ProjectModel> findById(@PathVariable UUID id) {
    return service
        .findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<ProjectModel>> findByUserId(@PathVariable UUID userId) {
    List<ProjectModel> projects = service.findByUserId(userId);
    return !projects.isEmpty() ? ResponseEntity.ok(projects) : ResponseEntity.noContent().build();
  }

  @PostMapping("/user/{userId}")
  public ResponseEntity<List<ProjectModel>> saveAll(
      @RequestBody List<ProjectUpsertDto> dtos, @PathVariable UUID userId) {
    var models = mapper.fromCreateDtosToModels(dtos, userId);
    var result = service.saveAll(models);
    return result != null
        ? ResponseEntity.status(201).body(result)
        : ResponseEntity.badRequest().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProjectModel> update(
      @PathVariable UUID id, @RequestBody ProjectUpsertDto dto) {
    return service
        .findById(id)
        .map(
            existingModel -> {
              mapper.updateModelFromDto(dto, existingModel);
              return ResponseEntity.ok(service.save(existingModel));
            })
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    boolean deleted = service.delete(id);
    return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}
