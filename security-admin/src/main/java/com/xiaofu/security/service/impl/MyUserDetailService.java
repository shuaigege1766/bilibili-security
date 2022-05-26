package com.xiaofu.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaofu.security.entity.AdminUserDetails;
import com.xiaofu.security.mbg.ums.entity.UmsAdmin;
import com.xiaofu.security.mbg.ums.entity.UmsResource;
import com.xiaofu.security.mbg.ums.service.UmsAdminService;
import com.xiaofu.security.mbg.ums.service.UmsResourceService;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UmsAdminService umsAdminService;

    @Autowired
    private UmsResourceService umsResourceService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //获取用户的信息
        UmsAdmin umsAdmin = umsAdminService.getOne(new LambdaQueryWrapper<UmsAdmin>().eq(UmsAdmin::getUsername, username));
        if (umsAdmin != null) {
            //获取用户的资源信息
            Set<UmsResource> resource = umsResourceService.getResourceByUserId(umsAdmin.getId());
            return  new AdminUserDetails(umsAdmin,resource);
        }
        throw new UsernameNotFoundException("用户名或者密码错误");
    }
}
