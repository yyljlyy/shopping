package com.luckin.innovation.group.service.impl;

import com.luckin.innovation.group.dao.SystemRoleRepository;
import com.luckin.innovation.group.entity.SystemRole;
import com.luckin.innovation.group.service.SystemRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author JasperLee
 * @decription Luckin Coffee CopyRight
 */
@Service
public class SystemRoleServiceImpl implements SystemRoleService {

    @Resource
    private SystemRoleRepository roleRepository;

    @Override
    public SystemRole save(SystemRole systemRole) {
        return roleRepository.save(systemRole);
    }

    @Override
    public void deleteSample(Long id) {

    }

    @Override
    public void deleteByIds(String[] split) {

    }

    @Override
    public List<SystemRole> sample(Long id) {
        return null;
    }

    @Override
    public List<SystemRole> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public SystemRole findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }
}
