package com.luckin.innovation.group.service;

import com.luckin.innovation.group.entity.SystemRole;
import com.luckin.innovation.group.entity.SystemUser;

import java.util.List;

/**
 * @author Jasper Lee (Jian) 创建于 2018-11-23 上午12:16
 * @company Luckin Coffe AI Group
 * @description com.luckin.innovation.group.service.impl
 * 版权所有 违法必究
 */
public interface SystemRoleService {
    SystemRole save(SystemRole systemRole);

    void deleteSample(Long id);

    void deleteByIds(String[] split);

    List<SystemRole> sample(Long id);

    List<SystemRole> findAll();

    SystemRole findById(Long id);

    void deleteById(Long id);

}
