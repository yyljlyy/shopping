package com.luckin.innovation.group.controller;

import com.luckin.innovation.group.utils.SwitchType;
import com.luckin.innovation.group.utils.gen.DatabaseGenerateService;
import com.luckin.innovation.group.utils.gen.MysqlGenerate;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jasper Lee (Jian) 创建于 2018-11-23 上午12:16
 * @company Luckin Coffe AI Group
 * @description com.luckin.innovation.group.service.impl
 * 版权所有 违法必究
 */
@Controller
public class GenCodeController {
    @Resource
    private MysqlGenerate dataSourceService;

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @RequestMapping("/genCode")
    public String gen(Model model){
        dataSourceService.setUrl(this.url);
        dataSourceService.setUserName(this.username);
        dataSourceService.setPassWord(this.password);
        model.addAttribute("dataList", dataSourceService.getDatabase());
        return "gen/index";
    }



    @RequestMapping("/generate/data-base")
    @ResponseBody
    public List dataBase(String database){
        dataSourceService.setUrl(this.url);
        dataSourceService.setUserName(this.username);
        dataSourceService.setPassWord(this.password);
        return dataSourceService.getSchema(database);
    }

    @RequestMapping("/generate/generateCode")
    @ResponseBody
    public List generateCode(String database, String datetable, String cate){
        generate(database,datetable,cate);
        return new ArrayList();
    }

    public void generate(String database, String datetable, String gente){
        dataSourceService.setUrl(this.url);
        dataSourceService.setUserName(this.username);
        dataSourceService.setPassWord(this.password);
        String remark = dataSourceService.getSchemaRemark(database, datetable);
        List column = dataSourceService.getColumn(database, datetable);
        for (String s : gente.split(",")) {
            //entity,dao,service,ctrl,page
            switch (s){
                case "entity":
                    build(datetable, column, "bean.btl", "Bean.java",remark);
                    System.out.println("Bean generate finish !");
                    break;
                case "dao":
                    build(datetable, column, "dao.btl", "Dao.java",remark);
                    System.out.println("dao generate finish !");
                    break;
                case "service":
                    build(datetable, column, "service.btl", "Service.java",remark);
                    System.out.println("service generate finish !");
                    build(datetable, column, "serviceimpl.btl", "ServiceImpl.java",remark);
                    System.out.println("serviceimpl generate finish !");
                    break;
                case "ctrl":
                    build(datetable, column, "controller.btl", "Controller.java",remark);
                    System.out.println("ctrl generate finish !");
                    break;
                case "page":
                    build(datetable, column, "html.btl", ".html",remark);
                    System.out.println("page generate finish !");
                    break;
                default:
            }
        }
    }




    private void build(String tableName, List columns, String file, String type, String remark) {
        String root = "";
        try {
            root = ResourceUtils.getURL("classpath:").getPath()+"/templates";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileResourceLoader resourceLoader = new FileResourceLoader(root,"utf-8");
        String project = System.getProperty("user.dir")+"/shopping-backend/src/main/";
        String className = DatabaseGenerateService.tableToJava(tableName, "");
        String actionName =  DatabaseGenerateService.tableToActionName(tableName,"");
        Configuration cfg = null;
        Template t = null;
        String projectMenu = "";
        String packPath = "java/com/luckin/innovation/group/";
        switch (file){
            case "bean.btl":
                projectMenu = packPath  + "entity/";
                break;
            case "dao.btl":
                projectMenu = packPath  + "dao/";
                break;
            case "service.btl":
                projectMenu = packPath  + "service/";
                break;
            case "serviceimpl.btl":
                projectMenu = packPath  + "service/impl/";
                break;
            case "controller.btl":
                projectMenu = packPath  + "controller/";
                break;
            case "html.btl":
                projectMenu = "resources/templates/";
                break;
            default:
        }
        try {
            cfg = Configuration.defaultConfiguration();
            GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
            gt.registerFunctionPackage("st", new SwitchType());
            t = gt.getTemplate("gen/" + file);
            t.binding("className",className);
            t.binding("tableName",tableName);
            t.binding("actionName",actionName);
            t.binding("columns",columns);
            t.binding("remark", remark);
            if("html.btl".equals(file)){
                SwitchType st = new SwitchType();
                String newPath = project + projectMenu + st.lower(st.ct(tableName));
                if (!Files.exists(Paths.get(newPath))){
                    try {
                        Files.createDirectory(Paths.get(newPath));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(newPath +"/"+ st.lower(className) + type);
                Files.write(Paths.get(newPath +"/"+ st.lower(className) + type), t.render().getBytes());
            }else {
                Files.write(Paths.get(project + projectMenu + className + type), t.render().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
