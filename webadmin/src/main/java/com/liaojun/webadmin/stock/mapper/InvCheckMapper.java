package com.liaojun.webadmin.stock.mapper;

import com.liaojun.component.mybatis.mapper.IBaseMapper;
import com.liaojun.webadmin.product.model.ProductSkuConfig;
import com.liaojun.webadmin.stock.model.InvCheck;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: yangzi
 * @Date: 2018/4/19 15:27
 */
@Mapper
public interface InvCheckMapper extends IBaseMapper<InvCheck> {
}
