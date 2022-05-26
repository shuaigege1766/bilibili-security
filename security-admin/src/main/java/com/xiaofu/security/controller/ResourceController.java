package com.xiaofu.security.controller;

import cn.hutool.http.server.HttpServerRequest;
import com.xiaofu.security.common.api.Result;
import com.xiaofu.security.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Api(tags = "资源模块")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;


    @ApiOperation("获取最新全部接口信息")
    @GetMapping("/listLastResource")
    public Result listLastResource(){
        List list = resourceService.getAllControllerInfo();
        return Result.success(list);
    }

}
