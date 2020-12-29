package com.security.services.dto.request.create;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import com.security.services.model.validators.EmailValidator;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class UserCreateDto {

    @NotNull
    @NotEmpty
    @ApiModelProperty(notes = "user name")
    private String username;

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    @EmailValidator
    private String email;

    @NotNull
    @Size(min = 6, max = 16, message = "Password Min Length should be 6 and max should be 16")
    @ApiModelProperty(notes = "User password")
    private String password;

    @ApiModelProperty(
            notes = "Indicates whether the user is enabled or disabled. A disabled user cannot be authenticated.")
    private boolean enabled;

    @NotNull
    @Positive
    private Long roleId;

    @NotNull
    @Positive
    private Long branchId;

}
