package dyhb.cv.auth.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dyhb.cv.auth.dto.LoginDto;
import dyhb.cv.auth.dto.RegisterDto;
import dyhb.cv.auth.exceptions.EmailExistsException;

@Service()
public class AuthService {
    @Autowired()
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserModel> findAll() {
        return this.userRepo.findAll();
    }

    public UserModel create(RegisterDto dto) throws EmailExistsException {
        Optional<UserModel> existingUser = userRepo.findByEmail(dto.getEmail());

        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        var user = UserModel.builder().email(dto.getEmail()).password(encodedPassword).build();

        if (existingUser.isPresent()) {
            throw new EmailExistsException(dto.getEmail());
        }

        return this.userRepo.save(user);
    }

    public Object login(LoginDto dto) {
        String accessToken = "biruleibeleibe";

        return accessToken;
    }
}
