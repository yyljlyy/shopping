package com.luckin.innovation.group.controller;

import com.luckin.innovation.group.entity.ResultMsg;
import com.luckin.innovation.group.entity.SystemRole;
import com.luckin.innovation.group.entity.SystemUser;
import com.luckin.innovation.group.service.SystemRoleService;
import com.luckin.innovation.group.service.impl.SystemUserServiceImpl;
import com.luckin.innovation.group.service.impl.SystemUserValidator;
import com.luckin.innovation.group.utils.Md5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

import static com.luckin.innovation.group.entity.ResultMsg.fail;
import static com.luckin.innovation.group.entity.ResultMsg.ok;

/**
 * @author Jasper Lee (Jian) 创建于 2018-11-23 上午12:16
 * @company Luckin Coffe AI Group
 * @description com.luckin.innovation.group.controller
 * 版权所有 违法必究
 */
@Controller
@RequestMapping("systemRole")
public class SystemRoleController {
    @Resource
    private SystemUserServiceImpl userService;

    @Resource
    private SystemRoleService roleService;

    /**
     * 查询全部数据操作
     *
     * @return
     */
    @RequestMapping(value = "all", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg all() {
        List<SystemRole> userList = roleService.findAll();
        ResultMsg result = new ResultMsg();
        result.setData(userList);
        return result;
    }


}
