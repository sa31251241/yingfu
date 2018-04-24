package com.liaojun.webadmin.web.product.controller;

import com.liaojun.component.base.common.model.Result;
import com.liaojun.component.base.constant.ComponentBaseConstant;
import com.liaojun.component.base.db.model.PageRequest;
import com.liaojun.component.base.db.model.PageResult;
import com.liaojun.component.base.db.model.SortRequest;
import com.liaojun.component.base.util.MapBuilder;
import com.liaojun.component.base.util.StringUtil;
import com.liaojun.component.mybatis.constant.ComponentMyBatisConstant;
import com.liaojun.component.web.model.ApiQueryRequest;
import com.liaojun.component.web.model.ApiRequest;
import com.liaojun.webadmin.base.controller.BaseController;
import com.liaojun.webadmin.product.model.ProductType;
import com.liaojun.webadmin.product.model.Vendor;
import com.liaojun.webadmin.product.service.IProductSkuConfigService;
import com.liaojun.webadmin.product.service.IProductSkuService;
import com.liaojun.webadmin.product.service.IProductTypeService;
import com.liaojun.webadmin.product.service.IVendorService;
import com.liaojun.webadmin.system.model.User;
import com.liaojun.webadmin.system.service.UserService;
import com.liaojun.webadmin.utils.SortRequestUtil;
import com.liaojun.webadmin.web.product.model.ProductTypeTree;
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
@RequestMapping("/product/productType")
public class ProductTypeController extends BaseController{

    @Autowired
    private IProductTypeService productTypeService;

    @RequestMapping("/tree")
    @ResponseBody
    public ProductTypeTree productTypeTree(){
        ProductTypeTree tree = productTypeService.getTree();
        return tree;
    }

    @RequestMapping("/list.html")
    public String toProductTypeList(){
        return "product/productTypeList";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String productTypeList(ApiQueryRequest apiQueryRequest){
        PageResult pageResult = productTypeService.findList(apiQueryRequest.populate(new ProductType()),apiQueryRequest.getPageRequest(), SortRequestUtil.buildCreateTimeAscSort());
        return printPageResult(pageResult);
    }

    @RequestMapping("/detail.html")
    public String productTypeDetail(String id,Model model){
        if(!StringUtil.isEmpty(id)){
            ProductType productType = productTypeService.findById(id);
            model.addAttribute("productType",productType);
        }
        return "product/productTypeDetail";
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Result vendorUpdate(@RequestBody ApiRequest apiRequest){
        return productTypeService.saveOrUpdate(apiRequest.populate(new ProductType()));
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Result vendorDelete(@RequestBody ApiRequest apiRequest){
        return productTypeService.deleteProductType(apiRequest.getParamString("id"));
    }

}
