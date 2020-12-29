package com.security.services.dto.mapper;

import java.util.Objects;
import com.security.services.dto.request.create.BranchCreateDto;
import com.security.services.dto.response.BranchResponseDto;
import com.security.services.model.Branch;


public interface BranchMapper {

    public static BranchResponseDto mapToDto(Branch branch) {
        if (Objects.isNull(branch)) return null;

        return BranchResponseDto.builder()
                                .address(branch.getAddress())
                                .contactNo(branch.getContactNumber())
                                .faxNo(branch.getFaxNumber())
                                .name(branch.getName())
                                .id(branch.getId())
                                .build();
    }

    public static Branch mapFromDto(BranchCreateDto dto) {

        return Branch.builder()
                     .address(dto.getAddress())
                     .contactNumber(dto.getContactNumber())
                     .faxNumber(dto.getFaxNumber())
                     .name(dto.getName())
                     .build();
    }

}
