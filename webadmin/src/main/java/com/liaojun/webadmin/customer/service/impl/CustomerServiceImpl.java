package com.liaojun.webadmin.customer.service.impl;

import com.liaojun.component.base.common.model.Result;
import com.liaojun.component.base.service.ResultBuilder;
import com.liaojun.component.base.util.DateUtil;
import com.liaojun.component.base.util.ExcelUtil;
import com.liaojun.component.base.util.StringUtil;
import com.liaojun.component.mybatis.service.impl.BaseServiceImpl;
import com.liaojun.webadmin.customer.mapper.CustomerMapper;
import com.liaojun.webadmin.customer.model.Customer;
import com.liaojun.webadmin.customer.service.CustomerService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer> implements CustomerService {


    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Class getEntityClass() {
        return Customer.class;
    }





    /**
     * 修改客戶信息
     * @param customer
     * @return
     */
    @Override
    public Result saveOrUpdate(Customer customer) {
        boolean isNew = false;
        if (StringUtil.isEmpty(customer.getId())) {
            isNew = true;
        }
        Result checkresult = checkParams(customer, isNew);
        if (!checkresult.isSuccess()) {
            return checkresult;
        }
        if (isNew) {
            customer.setAddTime(DateUtil.newDateString());
            save(customer);
            return ResultBuilder.saveSuccessResult();
        } else {
            update(customer);
            return ResultBuilder.updateSuccessResult();
        }
    }

    /**
     * 检验客户信息是否正确
     * @param customer
     * @param isNew
     * @return
     */
    private Result checkParams(Customer customer, boolean isNew) {
        if (exist("cusName", customer.getCusName())) {
            if (isNew) {
                return ResultBuilder.checkFailedResult("该客户已存在");
            }
        }
        if (StringUtil.isEmpty(customer.getCusName())) {
            return ResultBuilder.checkFailedResult("客户姓名必须输入");
        }
        if (StringUtil.isEmpty(customer.getCusSex())) {
            return ResultBuilder.checkFailedResult("客户性别必须输入");
        }
        if (StringUtil.isEmpty(customer.getCusPhone())) {
            return ResultBuilder.checkFailedResult("客户联系方式必须输入");
        }
        if (StringUtil.isEmpty(customer.getCusAddress())) {
            return ResultBuilder.checkFailedResult("客户地址必须输入");
        }
        return Result.EMPTY_SUCC_RESULT;
    }

    /**
     * 删除客户
     * @param id
     * @return
     */

    @Override
    public   Result customerDelete(String id){
        delete(id);
        return ResultBuilder.deleteSuccessResult();
    }

    @Override
    public void customerExport(List data, HttpServletResponse response)  {
        Map<String,String> map =new HashMap<>();
        map.put("cus_id","编号");
        map.put("cus_name","姓名");
        map.put("cus_sex","性别");
        map.put("cus_phone","电话");
        map.put("cus_address","地址");
        map.put("add_time","添加时间");
        map.put("remark","备注");
        HSSFWorkbook wb=ExcelUtil.getWorkbook(data,map);
       try{
           OutputStream outfile=response.getOutputStream();
           wb.write(outfile);
       }catch (Exception E){
           E.printStackTrace();
       }
    }
}
