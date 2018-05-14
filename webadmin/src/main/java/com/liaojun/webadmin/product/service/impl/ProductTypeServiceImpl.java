package com.liaojun.webadmin.product.service.impl;

import com.liaojun.component.base.common.model.Result;
import com.liaojun.component.base.constant.ComponentBaseConstant;
import com.liaojun.component.base.db.model.PageRequest;
import com.liaojun.component.base.db.model.PageResult;
import com.liaojun.component.base.db.model.SortRequest;
import com.liaojun.component.base.service.ResultBuilder;
import com.liaojun.component.base.util.BeanUtil;
import com.liaojun.component.base.util.IdGenerateUtil;
import com.liaojun.component.base.util.MapBuilder;
import com.liaojun.component.base.util.StringUtil;
import com.liaojun.component.mybatis.constant.ComponentMyBatisConstant;
import com.liaojun.component.mybatis.service.impl.BaseServiceImpl;
import com.liaojun.component.mybatis.service.impl.CriteriaUtil;
import com.liaojun.webadmin.product.mapper.ProductTypeMapper;
import com.liaojun.webadmin.product.model.ProductType;
import com.liaojun.webadmin.product.service.IProductSkuService;
import com.liaojun.webadmin.product.service.IProductTypeService;
import com.liaojun.webadmin.web.product.model.ProductTypeTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: yangzi
 * @Date: 2018/4/13 12:01
 */
@Service
public class ProductTypeServiceImpl extends BaseServiceImpl<ProductType> implements IProductTypeService {
    @Override
    public Class getEntityClass() {
        return ProductType.class;
    }

    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Autowired
    private IProductSkuService productSkuService;

    @Override
    public ProductTypeTree getTree() {
        List<ProductType> productTypeList = getProductTypeTree("-1",true);
        ProductTypeTree tree = convertProductTypeToTree(productTypeList);
        return tree;
    }

    @Override
    public Result saveOrUpdate(ProductType productType) {
        boolean isNew = false;
        if(StringUtil.isEmpty(productType.getId())){
            isNew = true;
        }
        Result checkResult =checkParams(productType,isNew);
        if(!checkResult.isSuccess()){
            return checkResult;
        }
        if(isNew){
            String nextId = getNextId(productType);
            productType.setId(nextId);
            save(productType);
        }else{
            update(productType);
        }
        return ResultBuilder.saveSuccessResult();
    }

    private String getNextId(ProductType productType) {
        Map<String,Object> params = MapBuilder.create().build();
        params.put("pid",productType.getPid());
        ProductType maxProductType = get(params, new SortRequest(ComponentMyBatisConstant.DB_KEY.ID.getValue(), ComponentBaseConstant.SORT_REQUEST_DIRECT.DESC.getValue()));
        if(maxProductType == null){
            if("-1".equals(productType.getPid())){
                //根节点下
                return IdGenerateUtil.getInstance().nextId().toString()+"0001";
            }
            return productType.getPid()+"0001";
        }
        return new BigDecimal(maxProductType.getId()).add(BigDecimal.ONE).toString();
    }

    @Override
    public Result deleteProductType(String id) {
        if(StringUtil.isEmpty(id)){
            return ResultBuilder.checkFailedResult("无效的物品分类");
        }
        Map<String, Object> params = MapBuilder.create(ComponentMyBatisConstant.DB_KEY.ID.getValue() + CriteriaUtil.DB_OPERATOR_MATCHES.RLK.getValue(), id).build();
        //TODO 待优化
        List<ProductType> productTypeList = getList(params);
        for (ProductType productType : productTypeList) {
            if (productSkuService.get("typeId", productType.getId()) != null) {
                return ResultBuilder.checkFailedResult("该分类或其子分类下还有商品，无法删除分类");
            }
        }
        delete(params);
        return ResultBuilder.deleteSuccessResult();
    }

    @Override
    public ProductType findById(String id) {
        return productTypeMapper.findById(id);
    }

    private Result checkParams(ProductType productType, boolean isNew) {
        if(!isNew){
            if(StringUtil.isEmpty(productType.getId())){
                return ResultBuilder.checkFailedResult("分类编码有误");
            }
        }
        if(StringUtil.isEmpty(productType.getName())){
            return ResultBuilder.checkFailedResult("分类名称不能为空");
        }
        if(StringUtil.isEmpty(productType.getPid())){
            return ResultBuilder.checkFailedResult("父分类不能为空");
        }
        return Result.EMPTY_SUCC_RESULT;
    }

    private ProductTypeTree convertProductTypeToTree(List<ProductType> productTypeList) {
        ProductTypeTree treeRoot = new ProductTypeTree();
        treeRoot.setName("全部分类");
        treeRoot.setSpread(true);
        treeRoot.setTypeId("-1");
        List<ProductTypeTree> productTypeTreeList = new ArrayList<>();
        for (ProductType productType : productTypeList) {
            ProductTypeTree productTypeTree = new ProductTypeTree();
            productTypeTree.setTypeId(productType.getId());
            productTypeTree.setName(productType.getName());
            productTypeTree.setSpread(true);
            productTypeTree.setChildren(productType.getChildrenTree());
            productTypeTreeList.add(productTypeTree);
        }
        treeRoot.setChildren(productTypeTreeList);
        return treeRoot;
    }

    public List<ProductType> getProductTypeTree(String pid,boolean recursion){
        List<ProductType> productTypeList = getList("pid", pid);
        if(!recursion){
            return productTypeList;
        }
        List<ProductType> productTypes = new ArrayList<ProductType>();
        for (ProductType productType : productTypeList) {
            productTypes.add(getProductTypeTree(productType.getId()));
        }
        return productTypes;
    }

    public ProductType getProductTypeTree(String id){
        ProductType productType = get(id);
        List<ProductType> productTypeList = getList("pid", id);
        if(CollectionUtils.isEmpty(productTypeList)){
            return productType;
        }
        List<ProductType> productTypes = new ArrayList<>();
        for (ProductType type : productTypeList) {
            productTypes.add(getProductTypeTree(type.getId()));
        }
        productType.setChildren(productTypes);
        return productType;
    }
}
