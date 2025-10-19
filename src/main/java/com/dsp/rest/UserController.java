package com.dsp.rest;

import com.dsp.dto.UserDto;
import com.dsp.entity.User;
import com.dsp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("role")
    public User assignRolesToUser(@RequestBody UserDto userDto) {
        return userService.assignRolesToUser(userDto);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
