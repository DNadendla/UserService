package com.dsp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String uname;
    private String password;
    private Long phno;
    private String token;

    private Set<RoleDto> roles;

    private List<Long> roleIds;
}
