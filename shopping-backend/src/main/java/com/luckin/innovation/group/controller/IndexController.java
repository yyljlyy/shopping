package com.luckin.innovation.group.controller;

import com.luckin.innovation.group.entity.ResultMsg;
import com.luckin.innovation.group.entity.SystemPermission;
import com.luckin.innovation.group.entity.SystemUser;
import com.luckin.innovation.group.service.SystemPermissionService;
import com.luckin.innovation.group.service.SystemRoleService;
import com.luckin.innovation.group.service.impl.SystemUserServiceImpl;
import com.luckin.innovation.group.service.impl.SystemUserValidator;
import com.luckin.innovation.group.utils.Md5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
public class IndexController {

    @Resource
    private SystemUserServiceImpl userService;

    @Resource
    private SystemUserValidator systemUserValidator;

    @Resource
    private SystemPermissionService permissionService;

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/index")
    public String indexPage() {
        return "index";
    }

    @RequestMapping(value="403.html",method = {RequestMethod.GET})
    public String error403() {
        return "403";
    }

    @RequestMapping(value="home",method = {RequestMethod.GET})
    public String home() {
        return "home";
    }

    @RequestMapping(value="getMenus", method ={RequestMethod.POST})
    @ResponseBody
    public ResultMsg getMenus(){
        SystemUser user = (SystemUser) SecurityUtils.getSubject().getPrincipal();
        if (user == null){
            return fail("登陆失效");
        }
        List<SystemPermission> coreMenus = permissionService.findMenuByUserName(user);
        ResultMsg result=new ResultMsg();
        result.setData(coreMenus);
        return result;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") SystemUser systemUserForm, BindingResult bindingResult, Model model) {
        systemUserValidator.validate(systemUserForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.addUser(systemUserForm);
        return "redirect:/index";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String login() {
        return "login";
    }


    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg loginDo(HttpServletRequest request) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        UsernamePasswordToken token = new UsernamePasswordToken(name, Md5Util.md5(password));
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (Exception e) {
            e.printStackTrace();
            return fail("用户名或密码错误！");
        }
        return ok();
    }

    @RequestMapping(value = "/loginOut", method = {RequestMethod.GET, RequestMethod.POST})
    public String loginOut() {
        SecurityUtils.getSubject().logout();
        return "login";
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String welcome() {
        return "index";
    }

}
