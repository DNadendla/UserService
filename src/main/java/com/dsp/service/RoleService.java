package com.dsp.service;

import com.dsp.dto.RoleDto;
import com.dsp.entity.Permission;
import com.dsp.entity.Role;
import com.dsp.repo.PermissionRepository;
import com.dsp.repo.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    private final RoleRepository roleRepo;
    private final PermissionRepository permissionRepo;

    public RoleService(RoleRepository roleRepo, PermissionRepository permissionRepo) {
        this.roleRepo = roleRepo;
        this.permissionRepo = permissionRepo;
    }

    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }

    public Role createRole(Role role) {
        return roleRepo.save(role);
    }

    public Role assignPerimissionsToRole(RoleDto roleDto) {
        Role role = roleRepo.findById(roleDto.getId()).orElseThrow(() -> new RuntimeException("Role not found"));
        Set<Permission> permissions = new HashSet<>(permissionRepo.findAllById(roleDto.getPermissionIds()));
        role.setPermissions(permissions);
        return roleRepo.save(role);
    }
}
