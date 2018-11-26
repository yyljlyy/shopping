package com.luckin.innovation.group.service;

import com.luckin.innovation.group.entity.SystemPermission;
import com.luckin.innovation.group.entity.SystemUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @author Jasper Lee (Jian) 创建于 2018-11-23 上午12:16
 * @company Luckin Coffe AI Group
 * @description com.luckin.innovation.group.service.impl
 * 版权所有 违法必究
 */
public interface SystemPermissionService {
    List<SystemPermission> findMenuByUserName(SystemUser username);

    SystemPermission save(SystemPermission permission);

    void deleteByIds(String[] split);

    void deleteById(Long id);

    void updateTemplateById(SystemPermission permission);

    SystemPermission findById(Long id);

    Page<SystemPermission> findAll(PageRequest pageRequest);

    List<SystemPermission> findAll();

    boolean findByUrl(String s);
}
