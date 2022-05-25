package com.xiaofu.security.service.impl;

import com.xiaofu.security.service.UmsAdminService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    @Override
    public UserDetails loadUserByUsername(String username) {
        return null;
    }
}
