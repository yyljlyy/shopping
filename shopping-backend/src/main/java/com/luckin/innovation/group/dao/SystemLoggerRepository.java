package com.luckin.innovation.group.dao;

import com.luckin.innovation.group.entity.SystemLogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jasper Lee (Jian) 创建于 2018-11-23 上午12:16
 * @company Luckin Coffe AI Group
 * @description com.luckin.innovation.group.service.impl
 * 版权所有 违法必究
 */
@Repository
public interface SystemLoggerRepository extends JpaRepository<SystemLogger,Long> {
}
