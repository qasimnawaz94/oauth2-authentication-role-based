package com.security.services.service;

import java.util.Collections;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.security.services.config.exceptions.RequestCannotProcessException;
import com.security.services.constants.RoleNames;
import com.security.services.dto.mapper.UserMapper;
import com.security.services.dto.request.create.UserCreateDto;
import com.security.services.dto.response.UserResponseDto;
import com.security.services.model.Role;
import com.security.services.model.User;
import com.security.services.repository.BranchRepository;
import com.security.services.repository.RoleRepository;
import com.security.services.repository.UserRepository;
import com.security.services.service.special.BaseService;


@Service
public class UsersBusinessService extends BaseService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserResponseDto saveUser(UserCreateDto dto) throws RequestCannotProcessException {

        User user = userCreationValidations(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setBranch(branchRepository.getOne(dto.getBranchId()));

        return UserMapper.mapToDto(userRepository.save(user));
    }


    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


    protected User userCreationValidations(UserCreateDto dto) throws RequestCannotProcessException {

        Optional<Role> role = findRoleById(dto.getRoleId());

        if (!role.isPresent()) {
            throw new RequestCannotProcessException(USER_ROLE_NOT_FOUND);
        }

        if (isRoleSuperAdmin(role.get())) {
            throw new RequestCannotProcessException(CANNOT_CREATE_SUPER_ADMIN);
        }

        if (existsByUsername(dto.getUsername())) {
            throw new RequestCannotProcessException(USER_NAME_DUPLICATE);
        }

        if (existsByEmail(dto.getEmail())) {
            throw new RequestCannotProcessException(USER_EMAIL_DUPLICATE);
        }

        if (!branchRepository.existsById(dto.getBranchId())) {
            throw new RequestCannotProcessException(BRANCH_NOT_FOUND);
        }

        User user = UserMapper.mapToUser(dto);
        user.setRoles(Collections.singleton(role.get()));

        return user;
    }

    protected Optional<Role> findRoleById(Long id) {
        return roleRepository.findById(id);
    }

    protected boolean isRoleSuperAdmin(Role role) {
        return RoleNames.SUPER_ADMIN_ROLE.equalsIgnoreCase(role.getName());
    }

}
