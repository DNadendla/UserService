package com.dsp.service;

import com.dsp.entity.Permission;
import com.dsp.repo.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepo;

    public PermissionService(PermissionRepository permissionRepo) {
        this.permissionRepo = permissionRepo;
    }

    public Permission createPermission(Permission permission) {
        return permissionRepo.save(permission);
    }

    public List<Permission> getAllPermissions() {
        return permissionRepo.findAll();
    }
}
