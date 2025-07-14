package dyhb.api.controller;

import dyhb.api.service.EducationService;
import dyhb.api.dto.EducationUpsertDto;
import dyhb.api.database.models.EducationModel;
import dyhb.api.mappers.EducationMapper;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/education")
@AllArgsConstructor
public class EducationController {

  @Autowired private EducationService service;

  private final EducationMapper mapper = Mappers.getMapper(EducationMapper.class);

  @GetMapping("/{id}")
  public ResponseEntity<EducationModel> findById(@PathVariable UUID id) {
    return service
        .findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<EducationModel>> findByUserId(@PathVariable UUID userId) {
    List<EducationModel> educations = service.findByUserId(userId);
    return !educations.isEmpty()
        ? ResponseEntity.ok(educations)
        : ResponseEntity.noContent().build();
  }

  @PostMapping("/user/{userId}")
  public ResponseEntity<List<EducationModel>> saveAll(
      @RequestBody List<EducationUpsertDto> dtos, @PathVariable UUID userId) {
    var models = mapper.fromCreateDtosToModels(dtos, userId);
    var result = service.saveAll(models);
    return result != null
        ? ResponseEntity.status(201).body(result)
        : ResponseEntity.badRequest().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<EducationModel> update(
      @PathVariable UUID id, @RequestBody EducationUpsertDto dto) {
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
