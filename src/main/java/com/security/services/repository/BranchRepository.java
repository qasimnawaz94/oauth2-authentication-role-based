package com.security.services.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.security.services.model.Branch;


public interface BranchRepository extends JpaRepository<Branch, Long> {

    Optional<Branch> findById(Long id);

    boolean existsById(Long id);

    boolean existsByName(String name);
}
