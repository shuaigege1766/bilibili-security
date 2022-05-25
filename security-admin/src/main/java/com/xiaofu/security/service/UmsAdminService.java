package com.xiaofu.security.service;


import org.springframework.security.core.userdetails.UserDetails;

public interface UmsAdminService {
    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);
}
