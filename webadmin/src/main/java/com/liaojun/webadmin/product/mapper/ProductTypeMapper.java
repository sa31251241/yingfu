package com.liaojun.webadmin.product.mapper;

import com.liaojun.component.base.db.model.PageRequest;
import com.liaojun.component.base.db.model.SortRequest;
import com.liaojun.component.mybatis.mapper.IBaseMapper;
import com.liaojun.webadmin.product.model.ProductType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: yangzi
 * @Date: 2018/4/13 12:56
 */
@Mapper
public interface ProductTypeMapper extends IBaseMapper<ProductType>{
    @Override
    List<ProductType> findList(@Param("productType") ProductType productType,@Param("pageRequest") PageRequest pageRequest,@Param("sortRequest") SortRequest sortRequest);

    ProductType findById(String id);

    @Override
    Integer findListCount(@Param("productType")ProductType productType);
}
