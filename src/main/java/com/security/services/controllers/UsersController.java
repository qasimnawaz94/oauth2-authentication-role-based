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
import com.security.services.dto.request.create.UserCreateDto;
import com.security.services.dto.response.UserResponseDto;
import com.security.services.service.UsersBusinessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@ApiController(value = "/users")
@Api(tags = "Users Controller", description = "User Operations")
public class UsersController extends BaseController {

    @Autowired
    private UsersBusinessService usersBusinessService;

    @ApiOperation(value = "Create User Endpoint", response = UserResponseDto.class)
    @PostMapping(value = ADD_USER)
    // @PreAuthorize("hasPermission(#createUserDto, 'WRITE_PRIVILEGE') ||
    // hasPermission(#createUserDto, 'ADD_USER') ")
    public ResponseEntity<RestResponseDto<UserResponseDto>> createUser(@Valid @RequestBody UserCreateDto createUserDto)
            throws RequestCannotProcessException {
        log.info("creating user %s", createUserDto.getUsername());
        return makeResponse(usersBusinessService.saveUser(createUserDto));
    }



}
