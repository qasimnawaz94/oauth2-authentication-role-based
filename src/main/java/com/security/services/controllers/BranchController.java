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
import com.security.services.dto.request.create.BranchCreateDto;
import com.security.services.dto.response.BranchResponseDto;
import com.security.services.service.BranchBusinessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@ApiController(value = "/branch")
@Api(tags = "Branch Controller", description = "Branch Operations")
public class BranchController extends BaseController {

    @Autowired
    private BranchBusinessService BranchBusinessService;

    @ApiOperation(value = "Create User Endpoint", response = BranchResponseDto.class)
    @PostMapping(value = ADD_BRANCH)
    public ResponseEntity<RestResponseDto<BranchResponseDto>> addBranch(@Valid @RequestBody BranchCreateDto dto)
            throws RequestCannotProcessException {
        log.debug("saving branch %s", dto.getName());
        return makeResponse(BranchBusinessService.saveBranch(dto));
    }

}
