package com.luckin.innovation.group.front.service.impl;

import com.luckin.innovation.group.front.dao.LoggerRepository;
import com.luckin.innovation.group.front.entity.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Jasper Lee (Jian) 创建于 2018-11-23 上午12:16
 * @company Luckin Coffe AI Group
 * @description com.luckin.innovation.group.service.impl
 * 版权所有 违法必究
 */
@Service(value = "loggerService")
public class LoggerService {
    @Resource
    private LoggerRepository loggerRepository;

    public Logger save(Logger permission) {
        return loggerRepository.save(permission);
    }
}
