package com.xiaofu.security.config;

import com.xiaofu.security.base.config.BaseSecurityConfig;
import com.xiaofu.security.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author 17662
 */
@EnableWebSecurity
@Configuration
public class AdminSecurityConfig extends BaseSecurityConfig {

    @Autowired
    private UmsAdminService UmsAdminService;

    @Override
    @Bean
    public UserDetailsService userDetailsService(){

        return  username -> UmsAdminService.loadUserByUsername(username);
    }

}
