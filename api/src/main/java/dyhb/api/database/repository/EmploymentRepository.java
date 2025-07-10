package dyhb.api.database.repository;

import dyhb.api.database.models.EmploymentModel;

import java.util.*;
import dyhb.api.database.repository.jpa.EmploymentJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmploymentRepository {

  @Autowired private final EmploymentJpaRepository jpaRepository;

  public Optional<EmploymentModel> findById(UUID id) {
    return jpaRepository.findById(id);
  }

  public List<EmploymentModel> findByUserId(UUID userId) {
    return jpaRepository.findByUserId(userId);
  }

  public EmploymentModel save(EmploymentModel model) {
    return jpaRepository.save(model);
  }

  public List<EmploymentModel> saveAll(List<EmploymentModel> models) {
    return jpaRepository.saveAll(models);
  }

  public boolean delete(UUID id) {
    return jpaRepository.deleteByUuid(id) == 1;
  }
}
