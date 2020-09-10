package com.xtech.taskwatch.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    USER;

    @Override
    public String getAuthority() {
        return USER.name();
    }
}
