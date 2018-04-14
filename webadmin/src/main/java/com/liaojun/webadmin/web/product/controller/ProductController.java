package com.liaojun.webadmin.web.product.controller;

import com.liaojun.webadmin.product.service.IProductSkuConfigService;
import com.liaojun.webadmin.product.service.IProductSkuService;
import com.liaojun.webadmin.product.service.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: yangzi
 * @Date: 2018/4/13 12:38
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductSkuService productSkuService;

    @Autowired
    private IProductSkuConfigService productSkuConfigService;

    @Autowired
    private IProductTypeService productTypeService;

    @RequestMapping("/productSkuList.html")
    public String toProductList(){
        return "product/productSkuList";
    }


    @RequestMapping("/productSkuDetail.html")
    public String toProductSkuDetail(){
        return "product/productSkuDetail";
    }
}
