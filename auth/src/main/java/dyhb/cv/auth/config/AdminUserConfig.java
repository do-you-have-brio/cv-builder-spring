package dyhb.cv.auth.config;

import dyhb.cv.auth.repositories.RoleRepository;
import dyhb.cv.auth.repositories.UserRepository;
import dyhb.cv.auth.user.Role;
import dyhb.cv.auth.user.UserModel;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
@AllArgsConstructor
public class AdminUserConfig implements CommandLineRunner {
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());
        var userAdmin = userRepository.findByEmail("victorestanislau1@gmail.com");

        userAdmin.ifPresentOrElse(
                user -> {
                    System.out.println("Admin user already exists, skipping creation.");
                },
                () -> {
                    var newUser = new UserModel();
                    newUser.setPassword(passwordEncoder.encode("admin"));
                    newUser.setRoles(Set.of(roleAdmin));
                    newUser.setEmail("victorestanislau1@gmail.com");
                    userRepository.save(newUser);
                }
        );
    }
}
