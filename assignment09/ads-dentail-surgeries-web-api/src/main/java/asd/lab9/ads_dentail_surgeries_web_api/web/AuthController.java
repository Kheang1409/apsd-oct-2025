package asd.lab9.ads_dentail_surgeries_web_api.web;

import asd.lab9.ads_dentail_surgeries_web_api.domain.Role;
import asd.lab9.ads_dentail_surgeries_web_api.domain.User;
import asd.lab9.ads_dentail_surgeries_web_api.repository.RoleRepository;
import asd.lab9.ads_dentail_surgeries_web_api.repository.UserRepository;
import asd.lab9.ads_dentail_surgeries_web_api.security.JwtService;
import asd.lab9.ads_dentail_surgeries_web_api.web.dto.JwtResponse;
import asd.lab9.ads_dentail_surgeries_web_api.web.dto.LoginRequest;
import asd.lab9.ads_dentail_surgeries_web_api.web.dto.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        Role role = roleRepository.findByName(request.role().toUpperCase()).orElseThrow();
        User user = User.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .build();
        user.getRoles().add(role);
        userRepository.save(user);
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", user.getRoles().stream().map(Role::getName).toList());
        String token = jwtService.generateToken(user.getUsername(), claims);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        User user = userRepository.findByUsername(request.username()).orElseThrow();
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", user.getRoles().stream().map(Role::getName).toList());
        String token = jwtService.generateToken(user.getUsername(), claims);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
