package com.luckin.innovation.group.controller;

import com.luckin.innovation.group.entity.SystemUser;
import com.luckin.innovation.group.service.impl.SystemUserServiceImpl;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;

import static org.junit.Assert.*;

public class SystemUserControllerTest {
    @Resource
    private SystemUserServiceImpl userService;

    @Test
    public void registration() {
        SystemUser systemUser = new SystemUser();
        systemUser.setUserName("admin");
        systemUser.setPassWord("admin");
        systemUser.setPhoneNumber("13846445567");
        systemUser.setEmail("admin@admin.com");
        systemUser.setCreateTime(new Date());
        userService.addUser(systemUser);
    }

    @Test
    public void registration1() {
    }

    @Test
    public void loginDo() {
    }

    @Test
    public void login() {
    }

    @Test
    public void welcome() {
    }
}