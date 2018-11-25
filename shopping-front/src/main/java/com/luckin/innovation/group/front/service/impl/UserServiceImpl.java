package com.luckin.innovation.group.front.service.impl;

import com.luckin.innovation.group.front.dao.UserRepository;
import com.luckin.innovation.group.front.entity.User;
import com.luckin.innovation.group.front.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Jasper Lee (Jian) 创建于 2018-11-23 上午12:16
 * @company Luckin Coffe AI Group
 * @description com.luckin.innovation.group.service.impl
 * 版权所有 违法必究
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUserName(username).get();
    }
}
