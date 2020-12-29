package com.security.services.controllers;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.security.services.controllers.base.BaseController;
import com.security.services.dto.common.RestResponseDto;
import com.security.services.dto.mapper.UserMapper;
import com.security.services.dto.response.UserResponseDto;
import com.security.services.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/authorize")
@Slf4j
@Api(tags = "Authentication API", description = "Authenticate user using authorization token.")
public class AuthenticationAPI extends BaseController {

    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "Authenticated User Login", response = UserResponseDto.class)
    @PostMapping(value = "/login")
    public ResponseEntity<RestResponseDto<UserResponseDto>> login(Principal principal) {

        String username = principal.getName();
        log.debug(String.format("logging in user %s", username));
        return makeResponse(UserMapper.mapToDto(userRepository.findOneByUsername(username)));
    }

}
