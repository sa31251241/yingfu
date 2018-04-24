package com.liaojun.webadmin.stock.mapper;

import com.liaojun.component.base.db.model.PageRequest;
import com.liaojun.component.base.db.model.PageResult;
import com.liaojun.component.base.db.model.SortRequest;
import com.liaojun.component.mybatis.mapper.IBaseMapper;
import com.liaojun.webadmin.stock.model.InvIn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: yangzi
 * @Date: 2018/4/19 15:30
 */
@Mapper
public interface InvInMapper extends IBaseMapper<InvIn> {
    List<InvIn> findList(@Param("invIn") InvIn invIn, @Param("pageRequest") PageRequest pageRequest, @Param("sortRequest") SortRequest sortRequest);

    Integer findListCount(@Param("invIn") InvIn invIn);

    InvIn findById(String id);
}
