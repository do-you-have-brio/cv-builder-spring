package dyhb.api.controller;

import dyhb.api.database.models.SocialAccountModel;
import dyhb.api.database.repository.SocialAccountRepository;
import dyhb.api.dto.SocialAccountUpsertDto;
import dyhb.api.mappers.SocialAccountMapper;
import java.util.*;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/social-account")
@AllArgsConstructor
public class SocialAccountController {

  @Autowired private SocialAccountRepository repository;

  private final SocialAccountMapper mapper = Mappers.getMapper(SocialAccountMapper.class);

  @GetMapping("/{id}")
  public ResponseEntity<SocialAccountModel> findById(@PathVariable UUID id) {
    return repository
        .findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<SocialAccountModel>> findByUserId(@PathVariable UUID userId) {
    List<SocialAccountModel> socialAccounts = repository.findByUserId(userId);
    return !socialAccounts.isEmpty()
        ? ResponseEntity.ok(socialAccounts)
        : ResponseEntity.noContent().build();
  }

  @PostMapping("/user/{userId}")
  public ResponseEntity<List<SocialAccountModel>> saveAll(
      @RequestBody List<SocialAccountUpsertDto> dtos, @PathVariable UUID userId) {
    var models = mapper.fromCreateDtosToModels(dtos, userId);
    var result = repository.saveAll(models);
    return result != null
        ? ResponseEntity.status(201).body(result)
        : ResponseEntity.badRequest().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<SocialAccountModel> update(
      @PathVariable UUID id, @RequestBody SocialAccountUpsertDto dto) {
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
