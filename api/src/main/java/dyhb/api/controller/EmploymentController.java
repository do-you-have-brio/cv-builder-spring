package dyhb.api.controller;

import dyhb.api.database.models.EmploymentModel;
import dyhb.api.service.EmploymentService;
import dyhb.api.dto.EmploymentUpsertDto;
import dyhb.api.mappers.EmploymentMapper;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/employment")
@AllArgsConstructor
public class EmploymentController {

  @Autowired private final EmploymentService service;

  private final EmploymentMapper mapper = Mappers.getMapper(EmploymentMapper.class);

  @GetMapping("/{id}")
  public ResponseEntity<EmploymentModel> findById(@PathVariable UUID id) {
    return service
        .findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<EmploymentModel>> findByUserId(@PathVariable UUID userId) {
    List<EmploymentModel> employments = service.findByUserId(userId);
    return !employments.isEmpty()
        ? ResponseEntity.ok(employments)
        : ResponseEntity.noContent().build();
  }

  @PostMapping("/user/{userId}")
  public ResponseEntity<List<EmploymentModel>> saveAll(
      @RequestBody List<EmploymentUpsertDto> dtos, @PathVariable UUID userId) {
    var models = mapper.fromCreateDtosToModels(dtos, userId);
    var result = service.saveAll(models);
    return result != null
        ? ResponseEntity.status(201).body(result)
        : ResponseEntity.badRequest().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<EmploymentModel> update(
      @PathVariable UUID id, @RequestBody EmploymentUpsertDto dto) {
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
