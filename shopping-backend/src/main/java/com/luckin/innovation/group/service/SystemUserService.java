package com.luckin.innovation.group.service;

import com.luckin.innovation.group.entity.SystemUser;

import java.util.List;

/**
 * @author Jasper Lee (Jian) 创建于 2018-11-23 上午12:16
 * @company Luckin Coffe AI Group
 * @description com.luckin.innovation.group.service.impl
 * 版权所有 违法必究
 */
public interface SystemUserService {

    List<SystemUser> getAllUsers();

    SystemUser getUserById(Long id);

    SystemUser getUserByLogin(String userName);

    void addUser(SystemUser systemUser);

    void updateUser(SystemUser systemUser);
}
