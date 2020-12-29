package com.security.services.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.security.services.config.ApiController;
import com.security.services.config.exceptions.RequestCannotProcessException;
import com.security.services.controllers.base.BaseController;
import com.security.services.dto.common.RestResponseDto;
import com.security.services.dto.request.create.RoleCreateDto;
import com.security.services.dto.response.RoleEndpointDto;
import com.security.services.service.RoleBusinessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@ApiController(value = "/role")
@Api(tags = "Role Controller", description = "Role Operations")
public class RoleController extends BaseController {

    @Autowired
    private RoleBusinessService roleBusinessService;

    @ApiOperation(value = "Create User Endpoint", response = RoleEndpointDto.class)
    @PostMapping(value = ADD_ROLE)
    public ResponseEntity<RestResponseDto<RoleEndpointDto>> addBranch(@Valid @RequestBody RoleCreateDto dto)
            throws RequestCannotProcessException {
        log.debug("saving role name %s", dto.getRoleName());
        return makeResponse(roleBusinessService.saveRole(dto));
    }

}
