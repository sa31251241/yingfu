package com.liaojun.webadmin.web.stock.controller;

import com.liaojun.component.base.common.model.Result;
import com.liaojun.component.base.db.model.PageResult;
import com.liaojun.component.web.model.ApiQueryRequest;
import com.liaojun.component.web.model.ApiRequest;
import com.liaojun.webadmin.base.constants.SysConstants;
import com.liaojun.webadmin.base.controller.BaseController;
import com.liaojun.webadmin.customer.model.Customer;
import com.liaojun.webadmin.customer.service.CustomerService;
import com.liaojun.webadmin.product.model.Vendor;
import com.liaojun.webadmin.stock.model.InvOutDetail;
import com.liaojun.webadmin.stock.model.InvOut;
import com.liaojun.webadmin.stock.service.IInvOutDetailService;
import com.liaojun.webadmin.stock.service.IInvOutService;
import com.liaojun.webadmin.system.model.User;
import com.liaojun.webadmin.system.service.UserService;
import com.liaojun.webadmin.utils.SortRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: yangzi
 * @Date: 2018/4/19 15:46
 */
@Controller
@RequestMapping("/stock/invOut")
public class InvOutController extends BaseController{

    @Autowired
    private IInvOutService invOutService;

    @Autowired
    private IInvOutDetailService invOutDetailService;
    @Autowired
    private UserService userService;
    @RequestMapping("/list.html")
    public String toInvOutList(Model model){

        List<User> userList = userService.getList();
        model.addAttribute("userlist",userList);
        return "stock/invOutList";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String invOutList(ApiQueryRequest apiRequest){
        InvOut invOut = invOutService.getBean(apiRequest.getNoEmptyParams());
        PageResult pageResult = invOutService.findList(invOut,apiRequest.getPageRequest(), SortRequestUtil.buildCreateTimeDescSort());
        return printPageResult(pageResult);
    }

    @RequestMapping("/detail.html")
    public String toDetail(String id,String type,Model model){
        InvOut invOut = invOutService.findById(id);
        model.addAttribute("invOut",invOut);
        model.addAttribute("typeList", SysConstants.valueDescMapCons.get("INVOUT_TYPE"));
        model.addAttribute("takeUserIdList",userService.getList());
        model.addAttribute("type",type);
        return "stock/invOutDetail";
    }

    @RequestMapping("/detail")
    @ResponseBody
    public String detail(ApiRequest apiRequest){
        List<InvOutDetail> InvOutDetailList = invOutDetailService.getList(apiRequest.getNoEmptyParams());
        return printListResult(InvOutDetailList);
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(@RequestBody ApiRequest apiRequest){
        InvOut invOut = invOutService.getBean(apiRequest.getNoEmptyParams());
        return invOutService.saveOrUpdate(invOut);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(@RequestBody ApiRequest apiRequest){
        return invOutService.deleteInvOut(apiRequest.getParamString("id"));
    }

    /**
     * 出库
     * @param apiRequest
     * @return
     */
    @RequestMapping("/outTreasury")
    @ResponseBody
    public Result outTreasury(@RequestBody ApiRequest apiRequest){
        return invOutService.outTreasury(apiRequest.getParamString("id"));
    }
}
