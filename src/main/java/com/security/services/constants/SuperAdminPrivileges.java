package com.security.services.constants;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import lombok.Getter;


@Getter
public enum SuperAdminPrivileges {

    READ_PRIVILEGE,
    WRITE_PRIVILEGE,
    UPDATE_PRIVILEGE;


    public static SimpleGrantedAuthority readAuthority() {
        return new SimpleGrantedAuthority(READ_PRIVILEGE.name());
    }

    public static SimpleGrantedAuthority writeAuthority() {
        return new SimpleGrantedAuthority(WRITE_PRIVILEGE.name());
    }

    public static SimpleGrantedAuthority updateAuthority() {
        return new SimpleGrantedAuthority(UPDATE_PRIVILEGE.name());
    }
}
