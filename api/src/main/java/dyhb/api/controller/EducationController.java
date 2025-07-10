package dyhb.api.controller;

import dyhb.api.database.repository.EducationRepository;
import dyhb.api.dto.CreateEducationDTO;
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

  @Autowired private EducationRepository repository;

  private final EducationMapper mapper = Mappers.getMapper(EducationMapper.class);

  @GetMapping("/{userId}")
  public ResponseEntity<List<EducationModel>> findByUserId(@PathVariable UUID userId) {
    List<EducationModel> educations = repository.findByUserId(userId);
    return !educations.isEmpty()
        ? ResponseEntity.ok(educations)
        : ResponseEntity.noContent().build();
  }

  @PostMapping("/{userId}")
  public ResponseEntity<EducationModel> save(
      @RequestBody CreateEducationDTO dto, @PathVariable UUID userId) {
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
