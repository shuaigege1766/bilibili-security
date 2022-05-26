package com.xiaofu.security.mbg.ums.service;

import com.xiaofu.security.mbg.ums.entity.UmsResource;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * <p>
 * 后台资源表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2022-05-26
 */
public interface UmsResourceService extends IService<UmsResource> {

    Set<UmsResource> getResourceByUserId(@Param("id") long id);
}
