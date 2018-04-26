package com.liaojun.webadmin.web.customer.controller;


import com.liaojun.component.base.db.model.PageResult;
import com.liaojun.component.web.model.ApiQueryRequest;
import com.liaojun.webadmin.base.controller.BaseController;
import com.liaojun.webadmin.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/customer/customer")
public class CustomerContrller extends BaseController {

    @Autowired
    private CustomerService customerService;
    //进入客户详情页面
    @RequestMapping("/list.html")
    public String tocustomerList(){
        return "customer/customerList";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String productSkuList(ApiQueryRequest apiQueryRequest){
        PageResult pageResult = customerService.getPageResult(apiQueryRequest.getParams(),apiQueryRequest.getPageRequest());
        return printPageResult(pageResult);
    }
}