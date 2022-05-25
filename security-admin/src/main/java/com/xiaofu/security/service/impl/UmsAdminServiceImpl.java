package com.xiaofu.security.service.impl;

import com.xiaofu.security.entity.AdminUserDetails;
import com.xiaofu.security.entity.UmsAdmin;
import com.xiaofu.security.entity.UmsResource;
import com.xiaofu.security.service.UmsAdminService;
import org.hibernate.validator.internal.util.privilegedactions.GetResource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsAdminServiceImpl implements UmsAdminService {


    @Override
    public UserDetails loadUserByUsername(String username) {
        //获取用户信息
        UmsAdmin admin = getAdminByUsername(username);
        if (admin != null) {
            List<UmsResource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin,resourceList);
        }
        throw new UsernameNotFoundException("用户名或者密码错误");
    }

    private List<UmsResource> getResourceList(Long id) {
        return null;
    }

    private UmsAdmin getAdminByUsername(String username) {
        return null;
    }


}
