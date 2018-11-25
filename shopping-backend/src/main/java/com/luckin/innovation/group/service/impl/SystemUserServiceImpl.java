package com.luckin.innovation.group.service.impl;

import com.luckin.innovation.group.dao.SystemUserRepository;
import com.luckin.innovation.group.entity.SystemUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.*;

/**
 * @author Jasper Lee (Jian) 创建于 2018-11-23 上午12:16
 * @company Luckin Coffe AI Group
 * @description com.luckin.innovation.group.service
 * 版权所有 违法必究
 */
@Service
public class SystemUserServiceImpl {

    @Resource
    private SystemUserRepository userRepository;

    @Transactional()
    public SystemUser findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userRepository.findByUserName(username).get();
    }

    public List<SystemUser> getAllUsers() {
        return new ArrayList<>(userRepository.findAll());
    }


    public SystemUser getUserById(Long id) {
        Optional<SystemUser> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public SystemUser getUserByLogin(String userName) {
        Optional<SystemUser> user = userRepository.findByUserName(userName);
        return user.orElse(null);
    }

    public void addUser(SystemUser systemUser) {
        systemUser.setCreateTime(new Date());
        userRepository.save(systemUser);
    }

    public void updateUser(SystemUser systemUser) {
        userRepository.save(systemUser);
    }


    public SystemUser insertUser(SystemUser user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteByIds(String[] split) {
        for (String s : split) {
            userRepository.deleteById(Long.valueOf(s));
        }
    }

    public SystemUser updateTemplateById(SystemUser user) {
        SystemUser updateUser = userRepository.findById(user.getId()).get();
        user.setUpdateTime(new Date());
        return userRepository.save(updateUser);
    }

    public Page<SystemUser> findPageBy(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    public SystemUser findOneById(Long id) {
        return userRepository.findById(id).get();
    }

    public List<SystemUser> findAll() {
        return userRepository.findAll();
    }
}
