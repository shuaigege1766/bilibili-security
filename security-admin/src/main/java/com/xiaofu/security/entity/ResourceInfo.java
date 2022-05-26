package com.xiaofu.security.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResourceInfo {
    String requestMethod;
    String uri;
    String controllerName;
    String controllerTag;
    String methodName;
    String methodDesc;

    public ResourceInfo(String requestMethod, String uri, String controllerName, String methodName) {
        this.requestMethod = requestMethod;
        this.uri = uri;
        this.controllerName = controllerName;
        this.methodName = methodName;
    }
}
