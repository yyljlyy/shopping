package com.luckin.innovation.group.service.impl;

import com.luckin.innovation.group.dao.SystemPermissionDao;
import com.luckin.innovation.group.dao.SystemUserRepository;
import com.luckin.innovation.group.entity.SystemPermission;
import com.luckin.innovation.group.entity.SystemRole;
import com.luckin.innovation.group.entity.SystemUser;
import com.luckin.innovation.group.service.SystemPermissionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Jasper Lee (Jian) 创建于 2018-11-23 上午12:16
 * @company Luckin Coffe AI Group
 * @description com.luckin.innovation.group.service.impl
 * 版权所有 违法必究
 */
@Service
public class SystemPermissionServiceImpl implements SystemPermissionService {

    @Resource
    private SystemPermissionDao permissionDao;
    @Resource
    private SystemUserRepository userRepository;

    @Override
    public List<SystemPermission> findMenuByUserName(SystemUser user) {
        ArrayList<SystemPermission> menus = new ArrayList<>();
        List<SystemRole> roles = user.getRoles();
        for (SystemRole role : roles) {
            for (SystemPermission permission : role.getPermissions()) {
                menus.add(permission);
                List<SystemPermission> childs = permissionDao.findAllByParentid(permission.getId());
                menus.addAll(childs);
            }
        }
        return menus;
    }

    @Override
    public SystemPermission save(SystemPermission permission) {
        return permissionDao.save(permission);
    }

    @Override
    public void deleteByIds(String[] split) {
        for (String id : split) {
            permissionDao.deleteById(Long.valueOf(id));
        }
    }

    @Override
    public void deleteById(Long id) {
        permissionDao.deleteById(id);
    }

    @Override
    public void updateTemplateById(SystemPermission permission) {
        permission.setUpdatetime(new Date());
        permissionDao.save(permission);
    }

    @Override
    public SystemPermission findById(Long id) {
        return permissionDao.findById(id).get();
    }

    @Override
    public Page<SystemPermission> findAll(PageRequest pageRequest) {
        return permissionDao.findAll(pageRequest);
    }

    @Override
    public List<SystemPermission> findAll() {
        return permissionDao.findAll();
    }
}
