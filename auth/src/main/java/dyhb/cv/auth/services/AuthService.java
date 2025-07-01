package dyhb.cv.auth.services;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import dyhb.cv.auth.dto.CreateUserDto;
import dyhb.cv.auth.dto.LoginRequest;
import dyhb.cv.auth.dto.LoginResponse;
import dyhb.cv.auth.exceptions.EmailExistsException;
import dyhb.cv.auth.user.Role;
import dyhb.cv.auth.repositories.RoleRepository;
import dyhb.cv.auth.user.UserModel;
import dyhb.cv.auth.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service()
@AllArgsConstructor
public class AuthService {
    @Autowired()
    private final UserRepository userRepo;

    @Autowired()
    private final RoleRepository roleRepository;

    @Autowired()
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired()
    private final JwtEncoder jwtEncoder;

    @Transactional
    public UserModel createUser(CreateUserDto dto) throws EmailExistsException {
        var basicRole = roleRepository.findByName(Role.Values.BASIC.name());

        if(userRepo.findByEmail(dto.email()).isPresent()) {
            throw new EmailExistsException(dto.email());
        }

        String encodedPassword = passwordEncoder.encode(dto.password());
        var user = UserModel.builder().email(dto.email()).password(encodedPassword).roles(Set.of(basicRole)).build();

        return this.userRepo.save(user);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        var user = userRepo.findByEmail(loginRequest.email())
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password"));

        if (!user.isLoginCorrect(loginRequest, passwordEncoder)) {
            throw new BadCredentialsException("Invalid username or password");
        }

        var expiresIn = 3600L;
        var now = Instant.now();

        var scopes = user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("authsecurity")
                .subject(user.getId().toString())
                .expiresAt(now.plusSeconds(expiresIn))
                .issuedAt(now)
                .claim("scope", scopes)
                .build();

        var jwt = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponse(jwt, expiresIn);
    }
}
