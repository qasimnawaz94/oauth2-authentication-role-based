package com.security.services.dto.request.create;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;


@Data
public class BranchCreateDto {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String address;

    @NotNull
    @NotEmpty
    private String contactNumber;

    @NotNull
    @NotEmpty
    private String faxNumber;

}
