package dyhb.api.database.repository;

import dyhb.api.database.models.ProfileModel;
import dyhb.api.database.repository.jpa.ProfileJpaRepository;
import java.util.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfileRepository {

  @Autowired private final ProfileJpaRepository jpaRepository;

  public Optional<ProfileModel> findById(UUID id) {
    return jpaRepository.findById(id);
  }

  public Optional<ProfileModel> findByUserId(UUID userId) {
    return jpaRepository.findByUserId(userId);
  }

  public ProfileModel save(ProfileModel model) {
    return jpaRepository.save(model);
  }

  public boolean delete(UUID id) {
    return jpaRepository.deleteByUuid(id) == 1;
  }
}
