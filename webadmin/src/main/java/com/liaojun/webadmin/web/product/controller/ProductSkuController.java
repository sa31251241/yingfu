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
import com.liaojun.webadmin.product.model.ProductSku;
import com.liaojun.webadmin.product.model.ProductSkuConfig;
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
@RequestMapping("/product/productSku")
public class ProductSkuController extends BaseController{

    @Autowired
    private IProductSkuService productSkuService;

    @Autowired
    private IProductSkuConfigService productSkuConfigService;

    @Autowired
    private IVendorService vendorService;

    @RequestMapping("/list.html")
    public String toProductList(){
        return "product/productSkuList";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String productSkuList(ApiQueryRequest apiQueryRequest){
        PageResult pageResult = productSkuService.findList(apiQueryRequest.populate(new ProductSku()),apiQueryRequest.getParamString("keyword") ,SortRequestUtil.buildCreateTimeAscSort(),apiQueryRequest.getPageRequest());
        return printPageResult(pageResult);
    }

    @RequestMapping("/detail.html")
    public String toProductSkuDetail(String id,Model model){
        if(!StringUtil.isEmpty(id)){
            ProductSku productSku = productSkuService.findById(id);
            model.addAttribute("productSku",productSku);
            ProductSkuConfig productSkuConfig = productSkuConfigService.get("productSkuId", productSku.getId());
            model.addAttribute("productSkuConfig",productSkuConfig);
        }
        //供应商列表
        List<Vendor> vendorList = vendorService.getList();
        model.addAttribute("vendorList",vendorList);
        return "product/productSkuDetail";
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(@RequestBody ApiRequest apiRequest){
        return productSkuService.saveOrUpdate(productSkuService.getBean(apiRequest.getParams()),productSkuConfigService.getBean(apiRequest.getParams()));
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(@RequestBody ApiRequest apiRequest){
        return productSkuService.deleteProductSku(apiRequest.getParamString("id"));
    }

    @RequestMapping("/search.html")
    public String toSearch(){
        return "product/productSkuSearch";
    }

}
