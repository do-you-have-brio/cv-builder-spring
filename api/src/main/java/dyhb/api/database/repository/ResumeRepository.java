package dyhb.api.database.repository;

import dyhb.api.database.models.ResumeModel;
import java.util.*;

import dyhb.api.database.repository.jpa.ResumeJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResumeRepository {

  @Autowired private final ResumeJpaRepository jpaRepository;

  public List<ResumeModel> findByUserId(UUID userId) {
    return jpaRepository.findByUserId(userId);
  }

  public ResumeModel save(ResumeModel model) {
    return jpaRepository.save(model);
  }
}
