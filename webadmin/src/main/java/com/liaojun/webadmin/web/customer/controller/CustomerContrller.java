package com.liaojun.webadmin.web.customer.controller;


import com.liaojun.component.base.common.model.Result;
import com.liaojun.component.base.db.model.PageResult;
import com.liaojun.component.web.model.ApiQueryRequest;
import com.liaojun.component.web.model.ApiRequest;
import com.liaojun.webadmin.base.controller.BaseController;
import com.liaojun.webadmin.customer.model.Customer;
import com.liaojun.webadmin.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/customer/customer")
public class CustomerContrller extends BaseController {

    @Autowired
    private CustomerService customerService;

    /**
     * 跳转到用户管理界面
     * @return
     */
    @RequestMapping("/list.html")
    public String tocustomerList(){
        return "customer/customerList";
    }


    /**
     * 查询客户列表信息
     * @param apiQueryRequest
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public String productSkuList(ApiQueryRequest apiQueryRequest){
        PageResult pageResult = customerService.getPageResult(apiQueryRequest.getParams(),apiQueryRequest.getPageRequest());
        return printPageResult(pageResult);
    }

    /**
     * 查询客户by  id
     * @param apiQueryRequest
     * @return
     */

    @RequestMapping("/findById")
    @ResponseBody
    public Result productFindById(@RequestBody  ApiQueryRequest apiQueryRequest){
        Customer customer = customerService.get(apiQueryRequest.getParamString("id"));
        return Result.EMPTY_SUCC_RESULT.setData(customer);
    }
    /**
     * 进入客户详情页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/detail.html")
    public String customerDetail(String id, Model model){
        Customer customer = customerService.get(id);
        model.addAttribute("customer",customer);
        model.addAttribute("id",id);
       return "customer/customerDetail";
    }

    /**
     * 修改客户信息
     * @param apiRequest
     * @return
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Result customerUpdate(@RequestBody ApiRequest apiRequest){
        return customerService.saveOrUpdate(apiRequest.populate(new Customer()));
    }

    /**
     * 删除客户
     * @param apiRequest
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Result customerDelete(@RequestBody ApiRequest apiRequest){
        return customerService.customerDelete(apiRequest.getParamString("id"));
    }



    /**
     * 导出所有客户
     * @param response
     * @return
     */
    @RequestMapping(value = "/export")
    @ResponseBody
    public void customerExport(HttpServletResponse response){
        List<Customer> customerList = customerService.getList();
        customerService.customerExport(customerList,response);
    }
}