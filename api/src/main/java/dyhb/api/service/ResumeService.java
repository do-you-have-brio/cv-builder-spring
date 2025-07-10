package dyhb.api.service;

import dyhb.api.database.models.ResumeModel;
import java.util.*;
import dyhb.api.database.repository.jpa.ResumeJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResumeService {

  @Autowired private final ResumeJpaRepository jpaRepository;

  public Optional<ResumeModel> findById(UUID id) {
    return jpaRepository.findById(id);
  }

  public List<ResumeModel> findByUserId(UUID userId) {
    return jpaRepository.findByUserId(userId);
  }

  public ResumeModel save(ResumeModel model) {
    return jpaRepository.save(model);
  }

  public List<ResumeModel> saveAll(List<ResumeModel> models) {
    return jpaRepository.saveAll(models);
  }

  public boolean delete(UUID id) {
    return jpaRepository.deleteByUuid(id) == 1;
  }
}
