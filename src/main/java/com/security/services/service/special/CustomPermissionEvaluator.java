package com.security.services.service.special;

import java.io.Serializable;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import com.security.services.constants.SuperAdminPrivileges;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {


    public CustomPermissionEvaluator() {}

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object targetUrl) {


        if (authentication == null || !authentication.isAuthenticated()) {
            log.debug(String.format("Not Allowed %s ", authentication));
            return false;
        }
        else {

            log
                    .debug(String
                            .format("User %s Permissions are %s %s ", authentication.getName(), System.lineSeparator(),
                                    authentication.getAuthorities()));

            if (checkSuperAdminPrivileges(authentication)) {
                return true;
            }

            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (authority.getAuthority().equals(targetUrl)) {
                    log.debug(String.format("User Allowed for %s ", targetUrl));
                    return true;
                }
            }
            log.debug(String.format("User NOT Allowed for %s ", targetUrl));
            return false;
        }
    }

    @Override
    public boolean hasPermission(Authentication arg0, Serializable arg1, String arg2, Object arg3) {
        throw new RuntimeException("Id-based permission evaluation not currently supported.");
    }

    // checking if User is Super admin
    private boolean checkSuperAdminPrivileges(Authentication authentication) {
        return authentication.getAuthorities().contains(SuperAdminPrivileges.readAuthority()) &&
            authentication.getAuthorities().contains(SuperAdminPrivileges.updateAuthority()) &&
            authentication.getAuthorities().contains(SuperAdminPrivileges.writeAuthority());
    }

}
