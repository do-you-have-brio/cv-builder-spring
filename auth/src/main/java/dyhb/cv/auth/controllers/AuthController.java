package dyhb.cv.auth.controllers;

import dyhb.cv.auth.dto.CreateUserDto;
import dyhb.cv.auth.dto.LoginRequest;
import dyhb.cv.auth.dto.LoginResponse;
import dyhb.cv.auth.exceptions.EmailExistsException;
import dyhb.cv.auth.services.AuthService;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {

    @Autowired
    private final AuthService authService;

    @Value("${hello.message}")
    private String helloMessage;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok(helloMessage);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> createUser(
        @RequestBody CreateUserDto dto
    ) throws EmailExistsException {
        var user = authService.createUser(dto);

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put(
            "message",
            "Usuário com email " + user.getEmail() + " criado com sucesso."
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
        @RequestBody LoginRequest loginRequest
    ) {
        var loginResponse = authService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
}
