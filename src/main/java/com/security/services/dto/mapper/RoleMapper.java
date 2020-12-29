package com.security.services.dto.mapper;

import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;
import com.security.services.dto.request.create.RoleCreateDto;
import com.security.services.dto.response.RoleEndpointDto;
import com.security.services.model.Role;


public interface RoleMapper {


    public static RoleEndpointDto mapToDto(Role role) {
        return RoleEndpointDto.builder()
                              .userRoleId(role.getId())
                              .userRoleName(role.getName())
                              .authorizedEndpoints(
                                      role.getPrivileges().stream().map(PrivilegeMapper::mapToDto).collect(Collectors.toList()))
                              .build();
    }

    public static Role mapFromDto(RoleCreateDto dto) {
        return Role.builder()
                   .name(dto.getRoleName())
                   .privileges(Objects.isNull(dto.getUsersPrivilegeIds())
                           ? Collections.emptyList()
                           : dto.getUsersPrivilegeIds().stream().map(PrivilegeMapper::mapFromId).collect(Collectors.toList()))
                   .build();
    }
}
