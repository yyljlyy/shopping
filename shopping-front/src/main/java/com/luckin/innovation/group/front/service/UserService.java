package com.luckin.innovation.group.front.service;

import com.luckin.innovation.group.front.entity.User;

/**
 * @author Jasper Lee (Jian) 创建于 2018-11-23 上午12:16
 * @company Luckin Coffe AI Group
 * @description com.luckin.innovation.group.service.impl
 * 版权所有 违法必究
 */
public interface UserService {
    User findByUsername(String username);
}
