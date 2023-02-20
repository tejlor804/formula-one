package hu.bredex.formula.one.auth;

import hu.bredex.formula.one.user.api.UserInput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody UserInput input
    ) {
        return ResponseEntity.ok(service.register(input));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody UserInput input
    ) {
        return ResponseEntity.ok(service.authenticate(input));
    }

    @GetMapping("/validateToken")
    public Boolean validateToken() {
        return true;
    }


}