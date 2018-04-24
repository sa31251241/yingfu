package com.liaojun.webadmin.web.stock.controller;

import com.liaojun.webadmin.stock.service.IInvInService;
import com.liaojun.webadmin.stock.service.IInvOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: yangzi
 * @Date: 2018/4/19 15:46
 */
@Controller
@RequestMapping("/stock/invOut")
public class InvOutController {

    @Autowired
    private IInvOutService invOutService;
}
