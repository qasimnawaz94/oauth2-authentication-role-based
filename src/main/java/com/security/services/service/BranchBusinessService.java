package com.security.services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.security.services.config.exceptions.RequestCannotProcessException;
import com.security.services.dto.mapper.BranchMapper;
import com.security.services.dto.request.create.BranchCreateDto;
import com.security.services.dto.response.BranchResponseDto;
import com.security.services.model.Branch;
import com.security.services.repository.BranchRepository;
import com.security.services.service.special.BaseService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class BranchBusinessService extends BaseService {

    @Autowired
    private BranchRepository branchRepository;


    public BranchResponseDto saveBranch(BranchCreateDto dto) throws RequestCannotProcessException {
        log.debug("checking if branch ' %s ' already exists", dto.getName());
        if (existsByName(dto.getName())) throw new RequestCannotProcessException(DUPLICATE_BRANCH_NAME);
        Branch branch = BranchMapper.mapFromDto(dto);
        return BranchMapper.mapToDto(branchRepository.save(branch));
    }


    public boolean existsByName(String name) {
        return branchRepository.existsByName(name);
    }
}
