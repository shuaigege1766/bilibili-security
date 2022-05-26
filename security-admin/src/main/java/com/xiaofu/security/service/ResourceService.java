package com.xiaofu.security.service;

import com.xiaofu.security.mbg.ums.entity.UmsResource;

import java.util.List;

public interface ResourceService {

    /**
     * 获取所有的接口信息和swagger注解信息
     * @return
     */
    List getAllControllerInfo();
}
