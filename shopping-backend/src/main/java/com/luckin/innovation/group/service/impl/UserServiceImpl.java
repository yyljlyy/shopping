package com.luckin.innovation.group.service.impl;

import com.luckin.innovation.group.dao.UserRepository;
import com.luckin.innovation.group.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Jasper Lee (Jian) 创建于 2018-11-23 上午12:16
 * @company Luckin Coffe AI Group
 * @description com.luckin.innovation.group.service
 * 版权所有 违法必究
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    @Resource
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return new ArrayList<>(userRepository.findAll());
    }


    public User getUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public User getUserByLogin(String login){
        Optional<User> user = userRepository.findByLogin(login);
        return user.orElse(null);
    }

    public void addUser(User user){
        user.setCreateTime(new Date());
        userRepository.save(user);
    }

    public void updateUser(User user){
        userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByLogin(login);
        if (user.isPresent()){
            return new UserAdapter(user.get());
        } else {
            throw new UsernameNotFoundException(String.format("User with login '%s' doesn't exist!", login));
        }
    }

    public User findByUsername(String userName) {
        return userRepository.findByUserName(userName);
    }
}
