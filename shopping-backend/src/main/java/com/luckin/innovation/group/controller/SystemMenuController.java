package com.luckin.innovation.group.controller;


import com.luckin.innovation.group.entity.ResultMsg;
import com.luckin.innovation.group.entity.SystemPermission;
import com.luckin.innovation.group.service.SystemPermissionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Jasper Lee (Jian) 创建于 2018-11-23 上午12:16
 * @company Luckin Coffe AI Group
 * @description com.luckin.innovation.group.service.impl
 * 版权所有 违法必究
 */
@Controller
@RequestMapping("systemMenu")
public class SystemMenuController {
    @Resource
    SystemPermissionService permissionService;

    /**
     * 进入列表页面
     * @return String
     */
    @RequestMapping(value="index",method = {RequestMethod.GET})
    public String index(){
        return "/menu/index";
    }

    /**
     * 进入编辑页面
     * @return String
     */
    @RequestMapping(value="edit",method = {RequestMethod.GET})
    public String edit(){
        return "menu/edit";
    }

    /**
     * 进入新增页面
     * @return String
     */
    @RequestMapping(value="add",method = {RequestMethod.GET})
    public String add(){
        return "menu/add";
    }

    /**
     * 添加操作
     * @param permission
     * @return ResultMsg
     */
    @RequestMapping(value = "add",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg add(SystemPermission permission) {
        SystemPermission saveMenu = permissionService.save(permission);
        ResultMsg result=new ResultMsg();
        result.setData(saveMenu);
        return result;
    }

    /**
     * 删除操作
     * @param permission
     * @return ResultMsg
     */
    @RequestMapping(value = "delete",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg delete(SystemPermission permission) {
        permissionService.deleteById(permission.getId());
        return new ResultMsg();
    }

    /**
     * 批量删除操作
     * @param id
     * @return ResultMsg
     */
    @RequestMapping(value = "deleteBatch",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg deleteBatch(String id) {
        permissionService.deleteByIds(id.split(","));
        return new ResultMsg();
    }

    /**
     * 编辑操作
     * @param permission
     * @return ResultMsg
     */
    @RequestMapping(value = "edit",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg update(SystemPermission permission) {
        permission.setUpdatetime(new Date());
        permissionService.updateTemplateById(permission);
        ResultMsg result=new ResultMsg();
        result.setData(permission);
        return result;
    }

    /**
     * 查询单条数据操作
     * @param permission
     * @return ResultMsg
     * @throws Exception
     */
    @RequestMapping(value = "findById",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg updateCoreMenu(SystemPermission permission) throws Exception{
        SystemPermission menu= permissionService.findById(permission.getId());
        ResultMsg result=new ResultMsg();
        result.setData(menu);
        return result;

    }

    /**
     * 查询全部数据操作
     * @return ResultMsg
     */
    @RequestMapping(value="all",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg all(){
        List<SystemPermission> coreMenus =permissionService.findAll();
        SystemPermission coreMenu=new SystemPermission();
        coreMenu.setId(0L);
        coreMenu.setName("/根目录");
        //向菜单list第一个位置插入根目录
        coreMenus.add(0,coreMenu);
        ResultMsg result=new ResultMsg();
        result.setData(coreMenus);
        return  result;
    }

    /**
     * 查询分页数据操作
     * @param pageNum
     * @param pageSize
     * @return ResultMsg
     */
    @RequestMapping(value="page",method = {RequestMethod.POST})
    @ResponseBody
    public ResultMsg page(Integer pageNum, Integer pageSize){
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize, Sort.by("id"));
        Page<SystemPermission> page = permissionService.findAll(pageRequest);
        ResultMsg result=new ResultMsg();
        result.setData(page);
        return  result;
    }
}
