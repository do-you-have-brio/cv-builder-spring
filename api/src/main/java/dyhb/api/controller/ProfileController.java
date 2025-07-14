package dyhb.api.controller;

import dyhb.api.database.models.ProfileModel;
import dyhb.api.service.ProfileService;
import dyhb.api.dto.ProfileUpsertDto;
import dyhb.api.mappers.ProfileMapper;
import java.util.*;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@AllArgsConstructor
public class ProfileController {

  @Autowired private ProfileService service;

  private final ProfileMapper mapper = Mappers.getMapper(ProfileMapper.class);

  @GetMapping("/{id}")
  public ResponseEntity<ProfileModel> findById(@PathVariable UUID id) {
    return service
        .findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<ProfileModel> findByUserId(@PathVariable UUID userId) {
    Optional<ProfileModel> profile = service.findByUserId(userId);
    return profile.map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
  }

  @PostMapping("/user/{userId}")
  public ResponseEntity<ProfileModel> save(
      @RequestBody ProfileUpsertDto dto, @PathVariable UUID userId) {
    var model = mapper.fromCreateDtoToModel(dto, userId);
    var result = service.save(model);
    return result != null
        ? ResponseEntity.status(201).body(result)
        : ResponseEntity.badRequest().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProfileModel> update(
      @PathVariable UUID id, @RequestBody ProfileUpsertDto dto) {
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
