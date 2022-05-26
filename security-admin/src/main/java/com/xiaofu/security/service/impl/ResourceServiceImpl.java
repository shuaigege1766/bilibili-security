package com.xiaofu.security.service.impl;

import com.xiaofu.security.base.util.SpringUtil;
import com.xiaofu.security.common.exception.Asserts;
import com.xiaofu.security.entity.ResourceInfo;
import com.xiaofu.security.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import springfox.documentation.swagger.web.ApiResourceController;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private WebApplicationContext applicationContext;

    @Override
    public List<ResourceInfo> getAllControllerInfo() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> methods = mapping.getHandlerMethods();
        List<ResourceInfo> res = new ArrayList<>(20);
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : methods.entrySet()){
            RequestMappingInfo key = entry.getKey();

            HandlerMethod value = entry.getValue();
            Class<?> aClass = value.getBeanType();
            //跳过springboot自带的controller
            if (aClass == BasicErrorController.class){
                continue;
            }
            //跳过swagger自带的controller
            if (aClass == ApiResourceController.class){
                continue;
            }
            try {
                RequestMethodsRequestCondition methodsCondition = key.getMethodsCondition();
                PatternsRequestCondition patternsCondition = key.getPatternsCondition();
                //请求方法
                String requestMethod = methodsCondition.getMethods().iterator().next().toString();
                //请求路径
                String uri = patternsCondition.getPatterns().iterator().next();
                //controller名称
                String controllerName = value.getBean().toString();
                //请求方法名
                String methodName = value.getMethod().getName();
                //但是还差一个swagger注解信息
                //controller模块
                ResourceInfo info = new ResourceInfo(requestMethod, uri, controllerName, methodName);
                Api api = aClass.getAnnotation(Api.class);
                if (api != null) {
                    String  controllerTag = api.tags()[0];
                    info.setControllerTag(controllerTag);
                }
                Method method = aClass.getMethod(methodName);
                ApiOperation methodAnnotation = method.getAnnotation(ApiOperation.class);
                if (methodAnnotation != null) {
                    //接口中方法描述
                    String methodDesc = methodAnnotation.value();
                    info.setMethodDesc(methodDesc);
                }
                res.add(info);
            } catch (NoSuchMethodException e) {
                log.error("错误{},信息：{}",e.getCause(),e.getMessage());
                Asserts.fail(e.getMessage());
            }
        }
        return res;
    }
}
