package com.liaojun.webadmin.web.index.controller;

import com.liaojun.webadmin.system.model.User;
import com.liaojun.webadmin.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: yangzi
 * @Date: 2018/4/11 17:00
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    public String index(Model model){
        List<User> userList = userService.getList();
        model.addAttribute("userList",userList);
        return "index";
    }
    @RequestMapping("/main.html")
    public String main(Model model){
        return "index/main";
    }

}