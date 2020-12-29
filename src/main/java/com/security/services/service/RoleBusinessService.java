package com.security.services.service;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.security.services.config.exceptions.RequestCannotProcessException;
import com.security.services.dto.mapper.RoleMapper;
import com.security.services.dto.request.create.RoleCreateDto;
import com.security.services.dto.response.RoleEndpointDto;
import com.security.services.model.Role;
import com.security.services.repository.PrivilegeRepository;
import com.security.services.repository.RoleRepository;
import com.security.services.service.special.BaseService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class RoleBusinessService extends BaseService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    public RoleEndpointDto saveRole(RoleCreateDto dto) throws RequestCannotProcessException {

        log.debug("checking if role name %s already exists ", dto.getRoleName());

        if (existsByName(dto.getRoleName())) {
            throw new RequestCannotProcessException(DUPLICATE_ROLE_NAME);
        }

        log.debug("checking if request contains invalid privilege ids ");

        if (Objects.nonNull(dto.getUsersPrivilegeIds()) &&
            privilegeRepository.countByIdIn(dto.getUsersPrivilegeIds()).intValue() != dto.getUsersPrivilegeIds().size()) {
            throw new RequestCannotProcessException(INVALID_PRIVILEGE_IDS);
        }

        Role role = RoleMapper.mapFromDto(dto);
        return RoleMapper.mapToDto(roleRepository.save(role));

    }

    public boolean existsByName(String roleName) {
        return roleRepository.existsByName(roleName);
    }
}
