package com.dsp.service;

import com.dsp.dto.UserDto;
import com.dsp.entity.Role;
import com.dsp.entity.User;
import com.dsp.repo.RoleRepository;
import com.dsp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder pwdEncoder;

    public UserService(UserRepository userRepo, RoleRepository roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    public User createUser(User user) {
        String encodedPwd = pwdEncoder.encode(user.getPassword());
        user.setPassword(encodedPwd);
        return userRepo.save(user);
    }

    public User assignRolesToUser(UserDto userDto) {
        User user = userRepo.findById(userDto.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Set<Role> roles = new HashSet<>(roleRepo.findAllById(userDto.getRoleIds()));
        user.setRoles(roles);

        return userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
