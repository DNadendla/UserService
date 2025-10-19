package com.dsp.rest;

import com.dsp.dto.UserDto;
import com.dsp.entity.Permission;
import com.dsp.entity.Role;
import com.dsp.entity.User;
import com.dsp.repo.UserRepository;
import com.dsp.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserRepository repo;

    public AuthController(AuthenticationManager authManager, JwtService jwtService, UserRepository repo) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.repo = repo;
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody UserDto request) {
        UserDto userDto = new UserDto();
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUname(), request.getPassword())
        );
        User user = repo.findByUname(request.getUname()).orElseThrow();

        Set<String> roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
        Set<String> permissions = user.getRoles().stream()
                .flatMap(r -> r.getPermissions().stream())
                .map(Permission::getName).collect(Collectors.toSet());
        userDto.setUname(user.getUname());
        userDto.setPhno(user.getPhno());
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setToken(jwtService.generateToken(user.getUname(), roles, permissions));
        return userDto;
    }
}
