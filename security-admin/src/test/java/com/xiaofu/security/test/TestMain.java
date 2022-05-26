package com.xiaofu.security.test;


import com.xiaofu.security.mbg.ums.entity.UmsResource;
import com.xiaofu.security.mbg.ums.service.UmsResourceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMain {

    @Autowired
    private UmsResourceService umsResourceService;

    @Test
    public void test() {
        Set<UmsResource> set = umsResourceService.getResourceByUserId(1L);
        System.out.println("set = " + set);
    }

}

