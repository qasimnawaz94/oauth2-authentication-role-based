package com.security.services.dto.response;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class BranchResponseDto {

    private Long id;
    private String name;
    private String address;
    private String contactNo;
    private String faxNo;

}
