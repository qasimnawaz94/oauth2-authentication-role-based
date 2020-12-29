package com.security.services.service.special;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.security.services.config.exceptions.ErrorMessages;
import com.security.services.constants.RoleNames;
import com.security.services.model.User;
import com.security.services.repository.UserRepository;


@Component
public class BaseService implements ErrorMessages {

    @Autowired
    @Lazy
    private UserRepository userRepository;

    public User getLoggedInUser() {
        org.springframework.security.core.userdetails.User user = getPrincipal();
        return findByUserName(user.getUsername());
    }

    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    public User findByUserName(String userName) {
        return userRepository.findOneByUsername(userName);
    }

    protected org.springframework.security.core.userdetails.User getPrincipal() {
        return (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    protected boolean isSuperAdmin() {
        return getLoggedInUser()
                .getRoles()
                .stream()
                .filter(o -> o.getName().equalsIgnoreCase(RoleNames.SUPER_ADMIN_ROLE))
                .count() > 0;
    }

}
