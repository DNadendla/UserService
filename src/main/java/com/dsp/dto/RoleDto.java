package com.dsp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class RoleDto {
    private Long id;
    private String name;

    private Set<PermissionDto> permissions;
    private Set<Long> permissionIds;
}
