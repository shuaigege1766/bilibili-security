package com.xiaofu.security.mbg.ums.mapper;

import com.xiaofu.security.mbg.ums.entity.UmsResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * <p>
 * 后台资源表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2022-05-26
 */
public interface UmsResourceMapper extends BaseMapper<UmsResource> {

    Set<UmsResource> getResourceByUserId(@Param("id") long id);
}
