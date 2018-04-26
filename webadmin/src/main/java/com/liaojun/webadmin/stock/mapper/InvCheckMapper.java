package com.liaojun.webadmin.stock.mapper;

import com.liaojun.component.base.db.model.PageRequest;
import com.liaojun.component.base.db.model.SortRequest;
import com.liaojun.component.mybatis.mapper.IBaseMapper;
import com.liaojun.webadmin.product.model.ProductSkuConfig;
import com.liaojun.webadmin.stock.model.InvCheck;
import com.liaojun.webadmin.stock.model.InvOut;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: yangzi
 * @Date: 2018/4/19 15:27
 */
@Mapper
public interface InvCheckMapper extends IBaseMapper<InvCheck> {
    List<InvCheck> findList(@Param("invCheck") InvCheck invCheck, @Param("pageRequest") PageRequest pageRequest, @Param("sortRequest") SortRequest sortRequest);

    Integer findListCount(@Param("invCheck") InvCheck invCheck);

    InvCheck findById(String id);
}
