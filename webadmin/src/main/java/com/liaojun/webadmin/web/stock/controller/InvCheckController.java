package com.liaojun.webadmin.web.stock.controller;

import com.liaojun.component.base.common.model.Result;
import com.liaojun.component.base.db.model.PageResult;
import com.liaojun.component.web.model.ApiQueryRequest;
import com.liaojun.component.web.model.ApiRequest;
import com.liaojun.webadmin.base.constants.SysConstants;
import com.liaojun.webadmin.base.controller.BaseController;
import com.liaojun.webadmin.stock.model.InvCheck;
import com.liaojun.webadmin.stock.model.InvOut;
import com.liaojun.webadmin.stock.model.InvOutDetail;
import com.liaojun.webadmin.stock.service.IInvCheckService;
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
 * @Date: 2018/4/19 15:48
 */
@Controller
@RequestMapping("/stock/invCheck")
public class InvCheckController extends BaseController{

    @Autowired
    private IInvCheckService invCheckService;

    @RequestMapping("/list.html")
    public String toInvCheckList(){
        return "stock/invCheckList";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String list(ApiQueryRequest apiRequest){
        PageResult pageResult = invCheckService.findList(apiRequest.populate(new InvCheck()), apiRequest.getPageRequest(), SortRequestUtil.buildCreateTimeDescSort());
        return printPageResult(pageResult);
    }

    @RequestMapping("/detail.html")
    public String toDetail(String id,String type,Model model){
        InvCheck invCheck = invCheckService.findById(id);
        model.addAttribute("invCheck",invCheck);
        model.addAttribute("type",type);
        return "stock/invCheckDetail";
    }

    @RequestMapping("update")
    @ResponseBody
    public Result update(@RequestBody ApiRequest apiRequest){
        InvCheck invCheck = invCheckService.getBean(apiRequest.getParams());
        return invCheckService.saveOrUpdate(invCheck);
    }

}
