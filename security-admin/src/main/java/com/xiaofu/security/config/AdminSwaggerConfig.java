package com.xiaofu.security.config;

import com.xiaofu.security.common.config.BaseSwaggerConfig;
import com.xiaofu.security.common.entity.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class AdminSwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        Boolean enable = true;

        return SwaggerProperties.builder()
                .apiBasePackage("com.xiaofu.security.controller")
                .title("后台系统")
                .description("后台相关接口文档")
                .contactName("xiaofu")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
