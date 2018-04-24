package com.liaojun.webadmin.web.stock.controller;

import com.liaojun.webadmin.stock.service.IInvCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: yangzi
 * @Date: 2018/4/19 15:48
 */
@Controller
@RequestMapping("/stock/invCheck")
public class InvCheckController {

    @Autowired
    private IInvCheckService invCheckService;
}
