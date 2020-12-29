package com.security.services.dto.common;

import java.io.Serializable;
import org.springframework.http.HttpStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class RestResponseDto<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    protected HttpStatus status = HttpStatus.OK; // HttpStatus.BAD_REQUEST

    @ApiModelProperty(value = "", required = true)
    protected T data;

    @ApiModelProperty(required = true)
    protected boolean success;

    public RestResponseDto() {
        setSuccess(false);
    }

    public RestResponseDto<T> makeSuccessResponse() {
        this.makeSuccessResponse(HttpStatus.OK);
        return this;
    }

    public RestResponseDto<T> makeSuccessResponse(HttpStatus status) {
        setSuccess(true);
        setStatus(status);
        return this;
    }

    public static <T> RestResponseDto<T> create(T data) {
        RestResponseDto<T> result = new RestResponseDto<T>();
        result.setData(data);
        result.setStatus(HttpStatus.OK);
        result.setSuccess(true);
        return result;
    }

    public static <T> RestResponseDto<T> create(T data, HttpStatus httpStatus) {
        RestResponseDto<T> result = new RestResponseDto<>();
        result.setData(data);
        result.setStatus(httpStatus);
        result.setSuccess(true);
        return result;
    }

    public RestResponseDto<T> makeSuccessResponse(T data) {
        this.makeSuccessResponse(data, HttpStatus.OK);
        return this;
    }

    public RestResponseDto<T> makeSuccessResponse(T data, HttpStatus status) {
        setSuccess(true);
        setStatus(status);
        setData(data);
        return this;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    private void setSuccess(boolean success) {
        this.success = success;
    }
}
