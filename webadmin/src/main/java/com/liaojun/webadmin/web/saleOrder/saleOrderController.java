package com.liaojun.webadmin.web.saleOrder;

import com.liaojun.component.base.common.model.Result;
import com.liaojun.component.base.db.model.PageResult;
import com.liaojun.component.web.model.ApiQueryRequest;
import com.liaojun.component.web.model.ApiRequest;
import com.liaojun.webadmin.base.constants.SysConstants;
import com.liaojun.webadmin.base.controller.BaseController;
import com.liaojun.webadmin.customer.model.Customer;
import com.liaojun.webadmin.customer.service.CustomerService;
import com.liaojun.webadmin.sale.model.SaleOrder;
import com.liaojun.webadmin.sale.service.SaleOrderDetailService;
import com.liaojun.webadmin.sale.service.SaleOrderService;
import com.liaojun.webadmin.stock.model.InvOut;
import com.liaojun.webadmin.utils.SortRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Wuzi on 2018/5/9.
 */
@Controller
@RequestMapping("/sale/order")
public class saleOrderController extends BaseController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private SaleOrderService saleOrderService;
    @Autowired
    private SaleOrderDetailService saleOrderDetailService;

    @RequestMapping("/list.html")
    public String toInvOutList(Model model){
        List<Customer> customerList = customerService.getList();
        model.addAttribute("customerList",customerList);
        return "sale/saleOrderList";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String SaleOrderList(ApiQueryRequest apiRequest){
        SaleOrder saleOrder = saleOrderService.getBean(apiRequest.getNoEmptyParams());
        PageResult pageResult = saleOrderService.findList(saleOrder,apiRequest.getPageRequest(), SortRequestUtil.buildCreateTimeDescSort());
        return printPageResult(pageResult);
    }


    @RequestMapping("/detail.html")
    public String toDetail(String id,String type,Model model){
        SaleOrder saleOrder = saleOrderService.findById(id);
        model.addAttribute("saleOrder",saleOrder);
        model.addAttribute("takeUserIdList",customerService.getList());
        model.addAttribute("type",type);
        return "sale/saleOrderDetail";
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(@RequestBody ApiRequest apiRequest){
        SaleOrder saleOrder = saleOrderService.getBean(apiRequest.getNoEmptyParams());
        return saleOrderService.saveOrUpdate(saleOrder);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(@RequestBody ApiRequest apiRequest){
        return saleOrderService.deleteSaleOrder(apiRequest.getParamString("id"));
    }
}
