package com.luckin.innovation.group.service.impl;

import com.luckin.innovation.group.ShoppingApplication;
import com.luckin.innovation.group.entity.SystemUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SystemSystemUserServiceImplTest {

    @Resource
    private SystemUserServiceImpl userService;

    @Test
    public void addUser() {
        SystemUser systemUser = new SystemUser();
        systemUser.setUserName("admin");
        systemUser.setPassWord("admin");
        systemUser.setPhoneNumber("13846445567");
        systemUser.setEmail("admin@admin.com");
        systemUser.setCreateTime(new Date());
        userService.addUser(systemUser);
    }
}