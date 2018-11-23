package com.luckin.innovation.group.service;

/**
 * @author Jasper Lee (Jian) 创建于 2018-11-23 上午12:16
 * @company Luckin Coffe AI Group
 * @description com.luckin.innovation.group.service
 * 版权所有 违法必究
 */
public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
