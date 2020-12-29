package com.security.services.dto.request.create;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;


@Data
public class RoleCreateDto {

    @NotNull
    @NotEmpty
    private String roleName;

    private List<Long> usersPrivilegeIds;
}
