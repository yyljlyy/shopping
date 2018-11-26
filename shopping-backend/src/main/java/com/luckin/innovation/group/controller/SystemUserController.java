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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.*;
import java.util.stream.Collectors;

import static com.luckin.innovation.group.entity.ResultMsg.fail;
import static com.luckin.innovation.group.entity.ResultMsg.ok;
import static org.springframework.data.domain.Sort.Direction.ASC;

/**
 * @author Jasper Lee (Jian) 创建于 2018-11-23 上午12:16
 * @company Luckin Coffe AI Group
 * @description com.luckin.innovation.group.controller
 * 版权所有 违法必究
 */
@Controller
@RequestMapping("systemUser")
public class SystemUserController {
    @Resource
    private SystemUserServiceImpl userService;

    @Resource
    private SystemRoleService roleService;

    @Resource
    private SystemUserValidator systemUserValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration() {
        return "registration";
    }

    /**
     * 进入列表页面
     *
     * @return
     */
    @RequestMapping(value = "index", method = {RequestMethod.GET})
    public String index() {
        return "user/index";
    }

    /**
     * 进入编辑页面
     *
     * @return
     */
    @RequiresPermissions("auth:user:edit")
    @RequestMapping(value = "edit", method = {RequestMethod.GET})
    public String edit() {
        return "user/edit";
    }

    /**
     * 进入新增页面
     *
     * @return
     */
    @RequestMapping(value = "add", method = {RequestMethod.GET})
    public String add() {
        return "user/add";
    }

    /**
     * 进入密码修改页面
     *
     * @return
     */
    @RequestMapping(value = "updatePwd", method = {RequestMethod.GET})
    public String updatePwd() {
        return "user/updatePwd";
    }

    /**
     * 添加操作
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "add", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg add(SystemUser user,String role) {
        //向用户表插入新添加用户信息
        SystemUser isuser = userService.findOneByName(user.getUserName());
        if (isuser != null){
            return fail("用户名已存在");
        }else {
            user.setPassWord(Md5Util.md5(user.getPassWord()));
            ArrayList<SystemRole> systemRoles = Arrays.stream(role.split(",")).map(s -> roleService.findById(Long.valueOf(s))).filter(Objects::nonNull).collect(Collectors.toCollection(ArrayList::new));
            user.setRoles(systemRoles);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            SystemUser user1 = userService.insertUser(user);
            ResultMsg result = new ResultMsg();
            result.setData(user1);
            return result;
        }

    }



    /**
     * 批量删除操作
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteBatch", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg deleteBatch(String id) {
        String[] ids = id.split(",");
        for (String s : ids) {
            SystemUser findUser = userService.findOneById(Long.valueOf(s));
            //根据多个用户id删除用户角色关系表信息
            List<SystemRole> roles = findUser.getRoles();
            for (SystemRole role : roles) {
                roleService.deleteById(role.getId());
            }
            //根据多个用户id删除用户表信息
            userService.deleteByIds(ids);
        }
        return ok();
    }

    /**
     * 编辑操作
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "edit", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg update(SystemUser user,String role) {
        //向用户角色关系表插入用户新修改的角色
        String[] roles = role.split(",");
        List<SystemRole> systemRoles = new ArrayList<>();
        for (String r : roles) {
            SystemRole roleById = roleService.findById(Long.valueOf(r));
            systemRoles.add(roleById);
        }
        user.setRoles(systemRoles);
        SystemUser updateUser = userService.updateTemplateById(user);
        roleService.deleteSample(updateUser.getId());
        ResultMsg result = new ResultMsg();
        result.setData(user);
        return result;
    }

    /**
     * 查询单条数据操作
     *
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "findById", method = {RequestMethod.POST}) //请求类型
    @ResponseBody
    public ResultMsg updateCoreMenu(SystemUser user) throws Exception {
        //向用户表查询用户信息
        SystemUser findUser = userService.findOneById(user.getId());
        ResultMsg result = new ResultMsg();
        result.setData(findUser);
        return result;

    }

    /**
     * 查询全部数据操作
     *
     * @return
     */
    @RequestMapping(value = "all", method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg all() {
        List<SystemUser> userList = userService.findAll();
        ResultMsg result = new ResultMsg();
        result.setData(userList);
        return result;
    }

    /**
     * 查询分页数据操作
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "page")
    @ResponseBody
    public ResultMsg page(Integer pageNum, Integer pageSize) {
        //查出用户分页集合
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        Page<SystemUser> pageBy = userService.findPageBy(pageRequest);
        ResultMsg result = new ResultMsg();
        result.setData(pageBy);
        return result;
    }

    /**
     * 修改用户密码操作
     *
     * @param oldPwd
     * @param newPwd
     * @param confirm
     * @return
     */
    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST, params = {"oldPwd", "newPwd", "confirm"})
    @ResponseBody
    public ResultMsg updatePwd(@RequestParam("oldPwd") String oldPwd, @RequestParam("newPwd") String newPwd, @RequestParam("confirm") String confirm) {
        if (oldPwd == null || oldPwd.length() <= 0 || newPwd == null || newPwd.length() <= 0 || confirm == null || confirm.length() <= 0) {
            return fail("三个密码都不能为空");
        }
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SystemUser user = userService.findByUsername(username);
        if (!user.getPassWord().equals(Md5Util.md5(oldPwd))) {
            return fail("密码输入错误");
        }
        user.setPassWord(Md5Util.md5(newPwd));
        userService.updateTemplateById(user);
        return ok();
    }


}
