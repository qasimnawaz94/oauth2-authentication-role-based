package com.security.services.controllers.base;

import org.springframework.http.ResponseEntity;
import com.security.services.constants.Endpoints;
import com.security.services.constants.RoleNames;
import com.security.services.dto.common.RestResponseDto;


public class BaseController implements RoleNames, Endpoints {

    /**
     * Creates a ResponseEntity to be sent back to client
     *
     * @param restResponseDto holding the response object and HTTP status
     * @return ResponseEntity with HTTP status and response object
     */
    public static <T> ResponseEntity<RestResponseDto<T>> response(RestResponseDto<T> restResponseDto) {
        return new ResponseEntity<>(restResponseDto, restResponseDto.getStatus());
    }

    /**
     * Creates a ResponseEntity to be sent back to client
     *
     * @param restResponseDto holding the response object and HTTP status
     * @return ResponseEntity with HTTP status and response object
     */
    public static <T> ResponseEntity<RestResponseDto<T>> makeResponse(T data) {
        return response(RestResponseDto.create(data));
    }

}
