package com.liaojun.webadmin.sale.mapper;

import com.liaojun.component.base.db.model.PageRequest;
import com.liaojun.component.base.db.model.SortRequest;
import com.liaojun.component.mybatis.mapper.IBaseMapper;
import com.liaojun.webadmin.sale.model.SaleOrder;
import com.liaojun.webadmin.stock.model.InvOut;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: yangzi
 * @Date: 2018/4/19 15:31
 */
@Mapper
public interface SaleOrderMapper extends IBaseMapper<SaleOrder>{


    @Override
    List<SaleOrder> findList(@Param("saleOrder") SaleOrder saleOrder, @Param("pageRequest") PageRequest pageRequest, @Param("sortRequest") SortRequest sortRequest);

    @Override
    Integer findListCount(@Param("saleOrder") SaleOrder saleOrder);

    SaleOrder findById(String id);
}
