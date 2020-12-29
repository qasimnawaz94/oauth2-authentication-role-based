package com.security.services.dto.mapper;

import java.util.stream.Collectors;
import com.security.services.dto.request.create.UserCreateDto;
import com.security.services.dto.response.UserResponseDto;
import com.security.services.model.Branch;
import com.security.services.model.User;


public interface UserMapper {

    public static UserResponseDto mapToDto(User user) {
        return UserResponseDto.builder()
                              .fullName(user.getFullName())
                              .userName(user.getUsername())
                              .rolesDetails(user.getRoles().stream().map(RoleMapper::mapToDto).collect(Collectors.toList()))
                              .userBranch(BranchMapper.mapToDto(user.getBranch()))
                              .build();
    }

    public static User mapToUser(UserCreateDto dto) {
        return User.builder()
                   .branch(Branch.builder().id(dto.getBranchId()).build())
                   .email(dto.getEmail())
                   .enabled(dto.isEnabled())
                   .firstName(dto.getFirstName())
                   .lastName(dto.getLastName())
                   .password(dto.getPassword())
                   .username(dto.getUsername())
                   .build();
    }
}
