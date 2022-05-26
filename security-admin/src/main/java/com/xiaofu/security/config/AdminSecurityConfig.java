package com.xiaofu.security.config;

import cn.hutool.json.JSONUtil;
import com.xiaofu.security.base.config.BaseSecurityConfig;
import com.xiaofu.security.common.api.IErrorCode;
import com.xiaofu.security.common.api.Result;
import com.xiaofu.security.common.api.ResultCode;
import com.xiaofu.security.service.impl.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        AuthenticationSuccessHandler handler = new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse,
                                                Authentication authentication) throws IOException, ServletException {
                httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
                httpServletResponse.setHeader("Cache-Control","no-cache");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.setContentType("application/json");
                PrintWriter writer = httpServletResponse.getWriter();
                writer.println(JSONUtil.parse(Result.success(ResultCode.SUCCESS)));
                writer.flush();
            }
        };
        return handler;
    }

}
