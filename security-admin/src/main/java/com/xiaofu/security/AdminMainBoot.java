package com.xiaofu.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import javax.swing.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class AdminMainBoot {

    private static final Logger log = LoggerFactory.getLogger(AdminMainBoot.class);
    private static Environment env;

    public AdminMainBoot(Environment env) {
        this.env = env;
    }

    public static void main(String[] args) {
        SpringApplication.run(AdminMainBoot.class, args);
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        String contextPath = "";
        String envProperty = env.getProperty("server.servlet.context-path");
        if (envProperty != null){
            contextPath = envProperty;
        }
        try {
            log.info("\n----------------------------------------------------------\n\t" +
                            "Application '{}' is running! Access URLs:\n\t" +
                            "Local: \t\t{}://localhost:{}" + "/swagger-ui.html" + " \t\tor " +  "{}://localhost:{}" + "/doc.html" +"\n\t" +
                            "External: \t{}://{}:{}" + "/swagger-ui.html" + "  or " +  "{}://{}:{}" + "/doc.html" +"\n\t" +
                            "Profile(s): \t{}\n----------------------------------------------------------",
                    env.getProperty("spring.application.name"),
                    protocol,
                    env.getProperty("server.port") + contextPath,

                    protocol,
                    env.getProperty("server.port") + contextPath,

                    protocol,
                    InetAddress.getLocalHost().getHostAddress(),
                    env.getProperty("server.port") + contextPath,

                    protocol,
                    InetAddress.getLocalHost().getHostAddress(),
                    env.getProperty("server.port") + contextPath,

                    env.getActiveProfiles());

            String configServerStatus = env.getProperty("configserver.status");
            if (configServerStatus == null) {
                configServerStatus = "Not found or not setup for this application";
            }
            log.info("\n----------------------------------------------------------\n\t" +
                            "Config Server: \t{}\n----------------------------------------------------------",
                    configServerStatus);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }


}
