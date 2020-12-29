package com.security.services.dto.mapper;

import java.util.Objects;
import com.security.services.dto.response.PrivilegeResponseDto;
import com.security.services.model.Privilege;


public interface PrivilegeMapper {

    public static PrivilegeResponseDto mapToDto(Privilege privilege) {
        return PrivilegeResponseDto.builder()
                                   .id(privilege.getId())
                                   .endpoint(privilege.getEndpoint())
                                   .name(privilege.getName())
                                   .pageUrl(privilege.getPageUrl())
                                   .build();
    }

    public static Privilege mapFromId(Long id) {
        if (Objects.isNull(id)) return null;
        return Privilege.builder().id(id).build();
    }
}
