package com.liaojun.webadmin.web.stock.controller;

import com.liaojun.component.base.common.model.Result;
import com.liaojun.component.base.db.model.PageResult;
import com.liaojun.component.web.model.ApiQueryRequest;
import com.liaojun.component.web.model.ApiRequest;
import com.liaojun.webadmin.base.constants.SysConstants;
import com.liaojun.webadmin.base.controller.BaseController;
import com.liaojun.webadmin.product.model.Vendor;
import com.liaojun.webadmin.product.service.IVendorService;
import com.liaojun.webadmin.stock.model.InvIn;
import com.liaojun.webadmin.stock.model.InvInDetail;
import com.liaojun.webadmin.stock.service.IInvInDetailService;
import com.liaojun.webadmin.stock.service.IInvInService;
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
@RequestMapping("/stock/invIn")
public class InvInController extends BaseController{

    @Autowired
    private IInvInService invInService;

    @Autowired
    private IVendorService vendorService;

    @Autowired
    private IInvInDetailService invInDetailService;

    @RequestMapping("/list.html")
    public String toInvInList(Model model){
        List<Vendor> vendorList = vendorService.getList();
        model.addAttribute("vendorList",vendorList);
        return "stock/invInList";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String invInList(ApiQueryRequest apiRequest){
        InvIn invIn = invInService.getBean(apiRequest.getNoEmptyParams());
        PageResult pageResult = invInService.findList(invIn,apiRequest.getPageRequest(), SortRequestUtil.buildCreateTimeDescSort());
        return printPageResult(pageResult);
    }

    @RequestMapping("/detail.html")
    public String toDetail(String id,String type,Model model){
        InvIn invIn = invInService.findById(id);
        model.addAttribute("invIn",invIn);
        List<Vendor> vendorList = vendorService.getList();
        model.addAttribute("vendorList",vendorList);
        model.addAttribute("typeList", SysConstants.valueDescMapCons.get("INVIN_TYPE"));
        model.addAttribute("type",type);
        return "stock/invInDetail";
    }

    @RequestMapping("/detail")
    @ResponseBody
    public String detail(ApiRequest apiRequest){
        List<InvInDetail> invInDetailList = invInDetailService.getList(apiRequest.getNoEmptyParams());
        return printListResult(invInDetailList);
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(@RequestBody ApiRequest apiRequest){
        InvIn invIn = invInService.getBean(apiRequest.getNoEmptyParams());
        return invInService.saveOrUpdate(invIn);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(@RequestBody ApiRequest apiRequest){
        return invInService.deleteInvIn(apiRequest.getParamString("id"));
    }

    /**
     * 入库
     * @param apiRequest
     * @return
     */
    @RequestMapping("/wareHousing")
    @ResponseBody
    public Result warehousing(@RequestBody ApiRequest apiRequest){
        return invInService.wareHousing(apiRequest.getParamString("id"));
    }
}
