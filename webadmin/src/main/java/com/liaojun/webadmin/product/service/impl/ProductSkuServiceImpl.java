package com.liaojun.webadmin.product.service.impl;

import com.liaojun.component.base.common.model.Result;
import com.liaojun.component.base.db.model.PageRequest;
import com.liaojun.component.base.db.model.PageResult;
import com.liaojun.component.base.db.model.SortRequest;
import com.liaojun.component.base.service.ResultBuilder;
import com.liaojun.component.base.util.BeanUtil;
import com.liaojun.component.base.util.StringUtil;
import com.liaojun.component.mybatis.service.impl.BaseServiceImpl;
import com.liaojun.webadmin.product.mapper.ProductSkuMapper;
import com.liaojun.webadmin.product.model.ProductSku;
import com.liaojun.webadmin.product.model.ProductSkuConfig;
import com.liaojun.webadmin.product.service.IProductSkuConfigService;
import com.liaojun.webadmin.product.service.IProductSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @Author: yangzi
 * @Date: 2018/4/13 11:59
 */
@Service
public class ProductSkuServiceImpl extends BaseServiceImpl<ProductSku> implements IProductSkuService{
    @Override
    public Class getEntityClass() {
        return ProductSku.class;
    }

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private IProductSkuConfigService productSkuConfigService;

    @Override
    public ProductSku findById(String id) {
        return productSkuMapper.findById(id);
    }

    @Override
    public PageResult findList(ProductSku productSku,String keyword, SortRequest sortRequest, PageRequest pageRequest) {
        if(!StringUtil.isEmpty(keyword)){
            productSku.setName(keyword);
            productSku.setSkuCode(keyword);
        }
        if("-1".equals(productSku.getTypeId())){
            productSku.setTypeId(null);
        }
       return super.findList(productSku, pageRequest,sortRequest);
    }

    @Override
    public Result saveOrUpdate(ProductSku productSku,ProductSkuConfig productSkuConfig) {
        boolean isNew = false;
        if(StringUtil.isEmpty(productSku.getId())){
            isNew = true;
        }
        Result checkResult = checkParams(productSku,productSkuConfig,isNew);
        if(!checkResult.isSuccess()){
            return checkResult;
        }
        if(isNew){
            save(productSku);
            productSkuConfig.setProductSkuId(productSku.getId());
            productSkuConfigService.save(productSkuConfig);
            return ResultBuilder.saveSuccessResult();
        }else{
            update(productSku);
            ProductSkuConfig skuConfig = productSkuConfigService.get("productSkuId", productSku.getId());
            skuConfig.setExpireWarning(productSkuConfig.getExpireWarning());
            skuConfig.setMaxStorage(productSkuConfig.getMaxStorage());
            skuConfig.setMinStorage(productSkuConfig.getMinStorage());
            productSkuConfigService.update(skuConfig);
            return ResultBuilder.updateSuccessResult();
        }
    }

    @Override
    public Result deleteProductSku(String id) {
        delete(id);
        return ResultBuilder.deleteSuccessResult();
    }

    private Result checkParams(ProductSku productSku,ProductSkuConfig productSkuConfig, boolean isNew) {
        if(StringUtil.isEmpty(productSku.getSkuCode())){
            return ResultBuilder.checkFailedResult("物品编号必须输入");
        }
        if(exist("skuCode",productSku.getSkuCode())) {
            if(isNew){
                return ResultBuilder.checkFailedResult("该物品编号已经存在");
            }
            if(!StringUtil.equals(get(productSku.getId()).getSkuCode(),productSku.getSkuCode())){
                //修改了skuCode
                return ResultBuilder.checkFailedResult("该物品编号已经存在");
            }
        }
        if(StringUtil.isEmpty(productSku.getName())){
            return ResultBuilder.checkFailedResult("物品名称必须输入");
        }
        if(StringUtil.isEmpty(productSku.getTypeId())){
            return ResultBuilder.checkFailedResult("物品分类必须选择");
        }
        if(productSkuConfig.getMinStorage()>productSkuConfig.getMaxStorage()){
            return ResultBuilder.checkFailedResult("最小库存数不能大于最大库存数");
        }
        return Result.EMPTY_SUCC_RESULT;
    }
}
