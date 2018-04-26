package com.liaojun.webadmin.web.stock.controller;

import com.liaojun.component.base.common.model.Result;
import com.liaojun.component.base.db.model.PageResult;
import com.liaojun.component.web.model.ApiQueryRequest;
import com.liaojun.webadmin.base.controller.BaseController;
import com.liaojun.webadmin.product.model.ProductSku;
import com.liaojun.webadmin.product.service.IProductSkuService;
import com.liaojun.webadmin.stock.service.IInvCheckService;
import com.liaojun.webadmin.stock.service.IInvSumService;
import com.liaojun.webadmin.utils.SortRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: yangzi
 * @Date: 2018/4/19 15:48
 */
@Controller
@RequestMapping("/stock/inv")
public class InvController extends BaseController{

    @Autowired
    private IProductSkuService productSkuService;

    @RequestMapping("/list.html")
    public String toInvList(){
        return "stock/invList";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String list(@RequestBody ApiQueryRequest apiQueryRequest){
        PageResult pageResult = productSkuService.findList(apiQueryRequest.populate(new ProductSku()),apiQueryRequest.getParamString("keyword") , SortRequestUtil.buildCreateTimeAscSort(),apiQueryRequest.getPageRequest());
        return printPageResult(pageResult);
    }
}
