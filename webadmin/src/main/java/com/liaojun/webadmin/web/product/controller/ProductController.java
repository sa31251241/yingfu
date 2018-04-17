package com.liaojun.webadmin.web.product.controller;

import com.liaojun.component.base.common.model.Result;
import com.liaojun.component.base.db.model.PageRequest;
import com.liaojun.component.base.db.model.PageResult;
import com.liaojun.component.base.util.MapBuilder;
import com.liaojun.component.base.util.StringUtil;
import com.liaojun.component.web.model.ApiQueryRequest;
import com.liaojun.component.web.model.ApiRequest;
import com.liaojun.webadmin.base.controller.BaseController;
import com.liaojun.webadmin.product.model.Vendor;
import com.liaojun.webadmin.product.service.IProductSkuConfigService;
import com.liaojun.webadmin.product.service.IProductSkuService;
import com.liaojun.webadmin.product.service.IProductTypeService;
import com.liaojun.webadmin.product.service.IVendorService;
import com.liaojun.webadmin.system.model.User;
import com.liaojun.webadmin.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yangzi
 * @Date: 2018/4/13 12:38
 */
@Controller
@RequestMapping("/product")
public class ProductController extends BaseController{

    @Autowired
    private IProductSkuService productSkuService;

    @Autowired
    private IProductSkuConfigService productSkuConfigService;

    @Autowired
    private IProductTypeService productTypeService;

    @Autowired
    private IVendorService vendorService;

    @Autowired
    private UserService userService;

    @RequestMapping("/productSkuList.html")
    public String toProductList(){
        Map<String,Object> params = new HashMap<>();
        params.put("userName__blk","ha");
        List<User> list = userService.getList(params);
        System.out.println(list);
        return "product/productSkuList";
    }


    @RequestMapping("/productSkuDetail.html")
    public String toProductSkuDetail(){
        return "product/productSkuDetail";
    }



    @RequestMapping("/vendorList.html")
    public String toVendorList(){
        return "product/vendorList";
    }

    @RequestMapping("/vendorList")
    @ResponseBody
    public String vendorList(ApiQueryRequest apiQueryRequest){
        PageResult pageResult = vendorService.getPageResult(apiQueryRequest.getNoEmptyParams(),apiQueryRequest.getPageRequest());
        return printPageResult(pageResult);
    }


    @RequestMapping("/vendorDetail.html")
    public String vendorDetail(String id,Model model){
        Vendor vendor = vendorService.get(id);
        model.addAttribute("vendor",vendor);
        model.addAttribute("id",id);
        return "product/vendorDetail";
    }

    @RequestMapping(value = "/vendorUpdate")
    @ResponseBody
    public Result vendorUpdate(@RequestBody ApiRequest apiRequest){
        return vendorService.saveOrUpdate(apiRequest.populate(new Vendor()));
    }

    @RequestMapping(value = "/vendorDelete")
    @ResponseBody
    public Result vendorDelete(@RequestBody ApiRequest apiRequest){
        return vendorService.vendorDelete(apiRequest.getParamString("id"));
    }
}
