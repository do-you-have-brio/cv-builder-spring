package dyhb.api.controller;

import dyhb.api.database.repository.ProjectRepository;
import dyhb.api.dto.CreateProjectDTO;
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

  @Autowired private final ProjectRepository repository;

  private final ProjectMapper mapper = Mappers.getMapper(ProjectMapper.class);

  @GetMapping("/{userId}")
  public ResponseEntity<List<ProjectModel>> findByUserId(UUID userId) {
    List<ProjectModel> projects = repository.findByUserId(userId);
    return !projects.isEmpty()
            ? ResponseEntity.ok(projects)
            : ResponseEntity.noContent().build();
  }

  @PostMapping("/{userId}")
  public ResponseEntity<ProjectModel> save(
      @RequestBody CreateProjectDTO dto, @PathVariable UUID userId) {
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
