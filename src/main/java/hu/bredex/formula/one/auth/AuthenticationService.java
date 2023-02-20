package hu.bredex.formula.one.auth;

import hu.bredex.formula.one.auth.jwt.JwtService;
import hu.bredex.formula.one.user.api.UserInput;
import hu.bredex.formula.one.user.entity.UserEntity;
import hu.bredex.formula.one.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostConstruct
    public void init() {
        UserEntity initialUser = new UserEntity();
        initialUser.setUsername("admin");
        initialUser.setPassword(passwordEncoder.encode("f1test2018"));
        userRepository.save(initialUser);
    }

    public AuthenticationResponse register(@Valid UserInput input) {
        UserEntity user = new UserEntity();
        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(UserInput input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );
        UserEntity user = userRepository.findByUsername(input.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
