package com.xiaofu.security.config;

import com.xiaofu.security.base.config.BaseSecurityConfig;
import com.xiaofu.security.service.impl.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author 17662
 */
@EnableWebSecurity
@Configuration
public class AdminSecurityConfig extends BaseSecurityConfig {


    @Autowired
    private MyUserDetailService userDetailService;

    @Override
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetailsService service = new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userDetailService.loadUserByUsername(username);
            }
        };
        return service;
    }

}
