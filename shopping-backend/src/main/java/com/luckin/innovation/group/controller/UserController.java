package com.luckin.innovation.group.controller;

import com.luckin.innovation.group.entity.ResultMsg;
import com.luckin.innovation.group.entity.User;
import com.luckin.innovation.group.service.SecurityService;
import com.luckin.innovation.group.service.impl.UserServiceImpl;
import com.luckin.innovation.group.service.impl.UserValidator;
import com.luckin.innovation.group.utils.CaptchaUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.luckin.innovation.group.entity.ResultMsg.fail;
import static com.luckin.innovation.group.entity.ResultMsg.ok;

/**
 * @author Jasper Lee (Jian) 创建于 2018-11-23 上午12:16
 * @company Luckin Coffe AI Group
 * @description com.luckin.innovation.group.controller
 * 版权所有 违法必究
 */
@Controller
public class UserController {
    @Resource
    private UserServiceImpl userService;

    @Resource
    private SecurityService securityService;

    @Resource
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration() {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.addUser(userForm);
        return "redirect:/index";
    }

    @ResponseBody
    @RequestMapping(value = "/loginDo")
    public ResultMsg loginDo(String name, String password, HttpServletRequest request) {
        if (!CaptchaUtils.checkVerifyCode(request)) {
            return fail("验证码有误！");
        }
        securityService.autoLogin(name, password);
        return ok();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "您的用户名密码错误.");
        }

        if (logout != null) {
            model.addAttribute("message", "您已成功登录.");
        }
        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
}
