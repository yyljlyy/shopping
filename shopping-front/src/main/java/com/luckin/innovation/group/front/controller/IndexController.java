package com.luckin.innovation.group.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jasper Lee (Jian) 创建于 2018-11-23 上午12:16
 * @company Luckin Coffe AI Group
 * @description com.luckin.innovation.group.service.impl
 * 版权所有 违法必究
 */
@Controller
public class IndexController {
    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/index")
    public String indexPage() {
        return "index";
    }

    @RequestMapping(value = "about.html")
    public String aboutPage() {
        return "about";
    }
    @RequestMapping(value = "buytoday.html")
    public String buytodayPage() {
        return "buytoday";
    }
    @RequestMapping(value = "commodity.html")
    public String commodityPage() {
        return "commodity";
    }
    @RequestMapping(value = "details.html")
    public String detailsPage() {
        return "details";
    }
    @RequestMapping(value = "information.html")
    public String informationPage() {
        return "information";
    }
    @RequestMapping(value = "login.html")
    public String loginPage() {
        return "login";
    }
    @RequestMapping(value = "shopcart.html")
    public String shopcartPage() {
        return "shopcart";
    }

}
