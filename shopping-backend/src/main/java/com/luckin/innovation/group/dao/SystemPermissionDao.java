package com.luckin.innovation.group.dao;

import com.luckin.innovation.group.entity.SystemPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jasper Lee (Jian) 创建于 2018-11-23 上午12:16
 * @company Luckin Coffe AI Group
 * @description com.luckin.innovation.group.service.impl
 * 版权所有 违法必究
 */
@Repository
public interface SystemPermissionDao extends JpaRepository<SystemPermission, Long> {
    List<SystemPermission> findAllByParentid(Long pid);
}
