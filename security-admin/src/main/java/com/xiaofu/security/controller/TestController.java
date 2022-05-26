package com.xiaofu.security.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "测试模块")
public class TestController {


    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
