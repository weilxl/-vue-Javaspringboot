package com.efficiency.security;

public class LoginUser {
    private Long userId;
    private String username;
    private String roleCode;

    public LoginUser(Long userId, String username, String roleCode) {
        this.userId = userId;
        this.username = username;
        this.roleCode = roleCode;
    }

    public Long getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getRoleCode() { return roleCode; }
}
