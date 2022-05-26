package com.xiaofu.security.mbg.ums.service.impl;

import com.xiaofu.security.mbg.ums.entity.UmsResource;
import com.xiaofu.security.mbg.ums.mapper.UmsResourceMapper;
import com.xiaofu.security.mbg.ums.service.UmsResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * <p>
 * 后台资源表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-05-26
 */
@Service
public class UmsResourceServiceImpl extends ServiceImpl<UmsResourceMapper, UmsResource> implements UmsResourceService {

    @Autowired
    private UmsResourceMapper umsResourceMapper;

    @Override
    public Set<UmsResource> getResourceByUserId(long id) {
        return  umsResourceMapper.getResourceByUserId(id);
    }
}
