package com.liaojun.webadmin.web.product.controller;

import com.liaojun.component.base.common.model.Result;
import com.liaojun.component.base.db.model.PageResult;
import com.liaojun.component.web.model.ApiQueryRequest;
import com.liaojun.component.web.model.ApiRequest;
import com.liaojun.webadmin.base.controller.BaseController;
import com.liaojun.webadmin.product.model.Vendor;
import com.liaojun.webadmin.product.service.IVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Author: yangzi
 * @Date: 2018/4/13 12:38
 */
@Controller
@RequestMapping("/product/vendor")
public class VendorController extends BaseController{

    @Autowired
    private IVendorService vendorService;

    @RequestMapping("/list.html")
    public String toVendorList(){
        return "product/vendorList";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String vendorList(ApiQueryRequest apiQueryRequest){
        PageResult pageResult = vendorService.getPageResult(apiQueryRequest.getNoEmptyParams(),apiQueryRequest.getPageRequest());
        return printPageResult(pageResult);
    }


    @RequestMapping("/detail.html")
    public String vendorDetail(String id,Model model){
        Vendor vendor = vendorService.get(id);
        model.addAttribute("vendor",vendor);
        model.addAttribute("id",id);
        return "product/vendorDetail";
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Result vendorUpdate(@RequestBody ApiRequest apiRequest){
        return vendorService.saveOrUpdate(apiRequest.populate(new Vendor()));
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Result vendorDelete(@RequestBody ApiRequest apiRequest){
        return vendorService.vendorDelete(apiRequest.getParamString("id"));
    }
}
