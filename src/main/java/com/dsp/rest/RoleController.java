package com.dsp.rest;

import com.dsp.dto.RoleDto;
import com.dsp.entity.Role;
import com.dsp.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    @PostMapping("/permission")
    public Role assignRoleToPermission(@RequestBody RoleDto roleDto) {
        return roleService.assignPerimissionsToRole(roleDto);
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }
}
