package com.security.services.config.exceptions;

public interface ErrorMessages {

    /*********************************************
     * USER MESSAGES
     **************************************************/
    public static final String USER_ROLE_NOT_FOUND = "This role does not exist!";
    public static final String USER_NAME_DUPLICATE = "User Name already exists";
    public static final String USER_EMAIL_DUPLICATE = "User Email Already Exists!";

    /*********************************************
     * Branch Messages
     **************************************************/
    public static final String BRANCH_NOT_FOUND = "This branch does not exist!";
    public static final String DUPLICATE_BRANCH_NAME = "Duplicate Branch Name!";


    /*********************************************
     * Role Messages
     **************************************************/
    public static final String CANNOT_CREATE_SUPER_ADMIN = "You are not allowed to create a super admin user";
    public static final String DUPLICATE_ROLE_NAME = "Role Already exists!";
    public static final String INVALID_PRIVILEGE_IDS = "Request contains invalid privilege ids!";


}
