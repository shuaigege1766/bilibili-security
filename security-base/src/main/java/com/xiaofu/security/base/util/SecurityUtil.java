package com.xiaofu.security.base.util;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


public class SecurityUtil {

    public static UserDetails getUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UsernamePasswordAuthenticationToken) {
            UserDetails userDetails = (UserDetails) principal;

        }
        return null;
    }

    public static Long getUserId(){
        UserDetails userDetails = getUserDetails();
        if (userDetails != null) {

        }
        return null;
    }
}
