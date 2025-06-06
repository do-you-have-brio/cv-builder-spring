package dyhb.cv.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dyhb.cv.auth.dto.LoginDto;
import dyhb.cv.auth.dto.RegisterDto;
import dyhb.cv.auth.exceptions.EmailExistsException;
import dyhb.cv.auth.exceptions.EmailNotFoundException;
import dyhb.cv.auth.user.AuthService;

@CrossOrigin
@RestController
public class AuthController {
    @Autowired()
    private AuthService authService;

    @GetMapping("")
    public String healthCheck() {
        return "OK";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto dto) throws EmailExistsException {
        return ResponseEntity.ok(this.authService.create(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) throws EmailNotFoundException {
        return ResponseEntity.ok(this.authService.login(dto));
    }
}
