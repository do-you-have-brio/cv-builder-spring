package dyhb.api.database.repository;

import dyhb.api.database.models.SocialAccountModel;
import dyhb.api.database.repository.jpa.SocialAccountJpaRepository;
import java.util.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SocialAccountRepository {

  @Autowired private final SocialAccountJpaRepository jpaRepository;

  public Optional<SocialAccountModel> findById(UUID id) {
    return jpaRepository.findById(id);
  }

  public List<SocialAccountModel> findByUserId(UUID userId) {
    return jpaRepository.findByUserId(userId);
  }

  public List<SocialAccountModel> saveAll(List<SocialAccountModel> models) {
    return jpaRepository.saveAll(models);
  }

  public SocialAccountModel save(SocialAccountModel model) {
    return jpaRepository.save(model);
  }

  public boolean delete(UUID id) {
    return jpaRepository.deleteByUuid(id) == 1;
  }
}
