package dyhb.api.database.repository;

import dyhb.api.database.models.JobModel;
import java.util.*;
import dyhb.api.database.repository.jpa.JobJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JobRepository {

  @Autowired private final JobJpaRepository jpaRepository;

  public Optional<JobModel> findById(UUID id) {
    return jpaRepository.findById(id);
  }

  public List<JobModel> findByUserId(UUID userId) {
    return jpaRepository.findByUserId(userId);
  }

  public JobModel save(JobModel model) {
    return jpaRepository.save(model);
  }

  public List<JobModel> saveAll(List<JobModel> models) {
    return jpaRepository.saveAll(models);
  }

  public boolean delete(UUID id) {
    return jpaRepository.deleteByUuid(id) == 1;
  }
}
