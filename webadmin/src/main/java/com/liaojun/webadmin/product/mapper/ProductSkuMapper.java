package com.liaojun.webadmin.product.mapper;

import com.liaojun.component.base.db.model.PageRequest;
import com.liaojun.component.base.db.model.SortRequest;
import com.liaojun.component.mybatis.mapper.IBaseMapper;
import com.liaojun.webadmin.product.model.ProductSku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: yangzi
 * @Date: 2018/4/13 12:56
 */
@Mapper
public interface ProductSkuMapper extends IBaseMapper<ProductSku> {

    ProductSku findById(String id);

    @Override
    List<ProductSku> findList(@Param("productSku") ProductSku productSku, @Param("pageRequest") PageRequest pageRequest, @Param("sortRequest") SortRequest sortRequest);

    @Override
    Integer findListCount(@Param("productSku") ProductSku productSku);

    List<ProductSku> findWarningList(@Param("productSku") ProductSku productSku,@Param("pageRequest") PageRequest pageRequest, @Param("sortRequest") SortRequest sortRequest);

    Integer findWarningListCount(@Param("productSku") ProductSku productSku);
}
