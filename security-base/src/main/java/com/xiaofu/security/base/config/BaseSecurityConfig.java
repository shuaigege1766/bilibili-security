package com.xiaofu.security.base.config;


import com.xiaofu.security.base.entity.IgnoreUrlsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.List;

/**
 * 子类会继承父类的注解
 */
public class BaseSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired(required = true)
    private UserDetailsService userDetailsService;

    @Autowired(required = true)
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry requests = http.authorizeRequests();
        List<String> urls = ignoreUrlsConfig().getUrls();
        for (String url : urls) {
            //放行白名单
            requests.antMatchers(url).permitAll();
        }
        requests
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll()//跨域请求放行
                .and()
                .formLogin()
                .successHandler(authenticationSuccessHandler)
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()           //其他请求都放行
                .and()
                .csrf()
                .disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).
                passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IgnoreUrlsConfig ignoreUrlsConfig() {
        return new IgnoreUrlsConfig();
    }
}
