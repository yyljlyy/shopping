package com.luckin.innovation.group.front.dao;

import com.luckin.innovation.group.front.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Jasper Lee (Jian) 创建于 2018-11-23 上午12:16
 * @company Luckin Coffe AI Group
 * @description com.luckin.innovation.group.dao
 * 版权所有 违法必究
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * 查询用户通过用户名
     * @param userName 登录标签
     * @return Optional<SystemUser>
     */
    Optional<User> findByUserName(String userName);
}
