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
@RequestMapping("/api/projects")
@AllArgsConstructor
public class ProjectsController {

  @Autowired private final ProjectRepository repository;

  private final ProjectMapper mapper = Mappers.getMapper(ProjectMapper.class);

  @GetMapping("/{userId}")
  public ResponseEntity<List<ProjectModel>> findByUserId(UUID userId) {
    List<ProjectModel> projects = repository.findByUserId(userId);
    if (projects.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(projects);
  }

  @PostMapping("/{userId}")
  public ResponseEntity<ProjectModel> save(
      @RequestBody CreateProjectDTO dto, @PathVariable UUID userId) {
    var model = mapper.fromCreateDTOtoModel(dto, userId);
    var result = repository.save(model);

    if (result == null) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.status(201).body(result);
  }
}
