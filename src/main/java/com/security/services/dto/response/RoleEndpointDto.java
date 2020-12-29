package com.security.services.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleEndpointDto {

    private Long userRoleId;
    private String userRoleName;
    private List<PrivilegeResponseDto> authorizedEndpoints;

}
