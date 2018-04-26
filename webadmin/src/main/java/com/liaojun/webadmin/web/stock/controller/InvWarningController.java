package com.liaojun.webadmin.web.stock.controller;

import com.liaojun.component.base.db.model.PageResult;
import com.liaojun.component.web.model.ApiQueryRequest;
import com.liaojun.component.web.model.ApiRequest;
import com.liaojun.webadmin.base.controller.BaseController;
import com.liaojun.webadmin.product.service.IProductSkuConfigService;
import com.liaojun.webadmin.product.service.IProductSkuService;
import com.liaojun.webadmin.stock.service.IInvSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 缺货统计
 * @Author: yangzi
 * @Date: 2018/4/19 15:51
 */
@Controller
@RequestMapping("/stock/invWarning")
public class InvWarningController extends BaseController{

    @Autowired
    private IProductSkuService productSkuService;

    @RequestMapping("/list.html")
    public String toInvWarningList(){
        return "stock/invWarningList";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String list(ApiQueryRequest apiQueryRequest){
        PageResult list = productSkuService.findWarningList(productSkuService.getBean(apiQueryRequest.getParams()),apiQueryRequest.getParamString("keyword"),apiQueryRequest.getPageRequest());
        return printPageResult(list);
    }
}
