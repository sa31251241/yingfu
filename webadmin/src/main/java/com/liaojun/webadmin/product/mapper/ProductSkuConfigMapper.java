package com.liaojun.webadmin.product.mapper;

import com.liaojun.component.base.db.model.PageRequest;
import com.liaojun.component.base.db.model.SortRequest;
import com.liaojun.component.mybatis.mapper.IBaseMapper;
import com.liaojun.webadmin.product.model.ProductSku;
import com.liaojun.webadmin.product.model.ProductSkuConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: yangzi
 * @Date: 2018/4/13 12:57
 */
@Mapper
public interface ProductSkuConfigMapper extends IBaseMapper<ProductSkuConfig>{

}
