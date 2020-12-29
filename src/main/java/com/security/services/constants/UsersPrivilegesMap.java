package com.security.services.constants;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum UsersPrivilegesMap {

    ADD_USER("ADD_USER", Endpoints.ADD_USER, ""),
    GET_USERS("GET_USERS", Endpoints.GET_USERS, ""),
    UPDATE_USERS("UPDATE_USERS", Endpoints.UPDATE_USERS, ""),
    DELETE_USERS("DELETE_USERS", Endpoints.DELETE_USERS, ""),

    ADD_BRANCH("ADD_BRANCH", Endpoints.ADD_BRANCH, ""),
    UPDATE_BRANCH("ADD_BRANCH", Endpoints.UPDATE_BRANCH, ""),
    DELETE_BRANCH("ADD_BRANCH", Endpoints.DELETE_BRANCH, ""),
    GET_BRANCHS("GET_BRANCHS", Endpoints.GET_BRANCHS, ""),

    ADD_ROLE("ADD_ROLE", Endpoints.ADD_ROLE, ""),
    UPDATE_ROLE("UPDATE_ROLE", Endpoints.UPDATE_ROLE, ""),
    DELETE_ROLE("DELETE_ROLE", Endpoints.DELETE_ROLE, ""),
    GET_ROLES("GET_ROLES", Endpoints.GET_ROLES, "");

    private static final Map<String, UsersPrivilegesMap> ID_BASED_MAP = new HashMap<>();

    static {
        Stream.of(UsersPrivilegesMap.values()).forEach(o -> ID_BASED_MAP.put(o.getName(), o));
    }

    private final String name;
    private final String endpoint;
    private final String pageUrl;

}
