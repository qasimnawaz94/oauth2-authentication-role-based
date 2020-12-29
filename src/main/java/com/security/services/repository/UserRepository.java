package com.security.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.security.services.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    /**
     *
     * @param username
     * @return @{@link User}
     */
    User findOneByUsername(String username);

    /**
     * 
     * @param username
     * @return
     */
    boolean existsByUsername(String username);

    /**
     * 
     * @param email
     * @return
     */
    boolean existsByEmail(String email);

}
