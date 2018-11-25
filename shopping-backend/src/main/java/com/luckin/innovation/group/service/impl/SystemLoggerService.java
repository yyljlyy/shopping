package com.luckin.innovation.group.service.impl;

import com.luckin.innovation.group.dao.SystemLoggerRepository;
import com.luckin.innovation.group.entity.SystemLogger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Jasper Lee (Jian) 创建于 2018-11-23 上午12:16
 * @company Luckin Coffe AI Group
 * @description com.luckin.innovation.group.service.impl
 * 版权所有 违法必究
 */
@Service(value = "loggerService")
public class SystemLoggerService {
    @Resource
    private SystemLoggerRepository loggerRepository;

    public SystemLogger save(SystemLogger permission) {
        return loggerRepository.save(permission);
    }
}
