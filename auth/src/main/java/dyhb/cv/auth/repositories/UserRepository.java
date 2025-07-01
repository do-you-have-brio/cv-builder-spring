package dyhb.cv.auth.repositories;

import java.util.Optional;
import java.util.UUID;

import dyhb.cv.auth.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    Optional<UserModel> findByEmail(String email);
}
